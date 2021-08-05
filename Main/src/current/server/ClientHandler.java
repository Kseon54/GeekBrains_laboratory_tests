package current.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {

    private final static Long TIME_LIMIT_CONNECT_SERVER = 120L * 1000L;

    private final Server server;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String name;

    private boolean isAlive;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            isAlive = true;

            new Thread(() -> {
                try {
                    doAuthentication();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection(socket);
                }
            })
                    .start();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void closeConnection(Socket socket) {
        server.unsubscribe(this);
        server.broadcastMessage(String.format("User[%s] is out.", name));

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    private void doAuthentication() throws IOException {
        sendMessage("Greeting you in the Outstanding Chat.");
        sendMessage("Please do authentication. Template is: -auth [login] [password]");

        Thread authenticationTime = CreateTimerCloseConnections();
        authenticationTime.start();

        while (isAlive) {
            String maybeCredentials = in.readUTF();
            if (!isAlive) return;
            /** sample: -auth login1 password1 */
            if (maybeCredentials.startsWith("-auth")) {
                String[] credentials = maybeCredentials.split("\\s");

                Optional<AuthService.Entry> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if (maybeUser.isPresent()) {
                    AuthService.Entry user = maybeUser.get();
                    if (server.isNotUserOccupied(user.getName())) {
                        name = user.getName();
                        sendMessage("AUTH OK.");
                        sendMessage("Welcome.");
                        server.broadcastMessage(String.format("User[%s] entered chat.", name));
                        server.subscribe(this);
                        authenticationTime.interrupt();
                        return;
                    } else {
                        sendMessage("Current user is already logged in");
                    }
                } else {
                    sendMessage("Invalid credentials.");
                }
            } else {
                sendMessage("Invalid auth operation");
            }
        }
    }

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            if (inboundMessage.equals("-exit")) {
                break;
            }
            server.broadcastMessage(inboundMessage);
        }
    }

    private Thread CreateTimerCloseConnections() {
        return new Thread(() -> {
            long timeStart = System.currentTimeMillis();
            while (!Thread.interrupted()) {
                if (System.currentTimeMillis() - timeStart >= TIME_LIMIT_CONNECT_SERVER) {
                    sendMessage("Time Out");
                    isAlive = false;
                    return;
                }
            }
        });
    }

    public boolean isAlive() {
        return isAlive;
    }
}
