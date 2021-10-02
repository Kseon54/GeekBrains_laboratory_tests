package current.server;

import current.server.db.model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {

    private final static Long TIME_LIMIT_CONNECT_SERVER = 20L * 1000L;

    private final Server server;
    private final DataInputStream in;
    private final DataOutputStream out;
    private User user;

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
        server.broadcastMessage("Server", String.format("User[%s] is out.", user.getNick()));

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
        return user.getNick();
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

                Optional<User> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if (maybeUser.isPresent()) {
                    this.user = maybeUser.get();
                    if (server.isNotUserOccupied(this.user.getNick())) {
                        sendMessage("AUTH OK.");
                        sendMessage("Welcome.");
                        server.broadcastMessage("Server", String.format("User[%s] entered chat.", this.user.getNick()));
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
            if (inboundMessage.startsWith("-rename")) {
                String[] credentials = inboundMessage.split("\\s");
                String name = user.getNick();
                boolean complete = server.rename(user, credentials[1], credentials[2]);
                if (complete) server.broadcastMessage(
                        "Server", name + " update nickname, his new nickname: " + credentials[1]);
                else sendMessage("Sorry your nickname is unchanged");
                continue;
            }
            server.broadcastMessage(user.getNick(), inboundMessage);
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
