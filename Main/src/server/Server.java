package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Server {

    private ServerSocket serverSocket;
    private final List<ClientHandler> loggedUser;
    private final AuthService authService;

    public Server() {
        authService = new AuthService();
        loggedUser = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void subscribe(ClientHandler user) {
        loggedUser.add(user);
    }

    public void unsubscribe(ClientHandler user) {
        loggedUser.remove(user);
    }

    public boolean isNotUserOccupied(String name) {
        return !isUserOccupied(name);
    }

    public boolean isUserOccupied(String name) {
        /**
         for(ClineHandler user : loggedUser) {
         if (user.getName().equals(user)) {
         return true;
         }
         }
         return false;
         */

        /**
         loggedUser.stream()
         .filter(u -> u.getName().equals(u))
         .findFirst()
         .isPresent();
         */

        return loggedUser.stream()
                .anyMatch(u -> u.getName().equals(name));
    }

    public void broadcastMessage(String outboundMessage) {
        /**
         for (ClientHandler user: loggedUser) {
         user.sendMessage(outboundMessage);
         }
         */

        /**
         loggedUser.forEach(new Consumer<ClientHandler>() {
        @Override public void accept(ClientHandler clientHandler) {
        clientHandler.sendMessage(outboundMessage);
        }
        });
         */

        loggedUser.forEach(clientHandler -> clientHandler.sendMessage(outboundMessage));
    }

    public void privateMessage(String recipientUserName, String outboundMessage) {
        Optional<ClientHandler> maybeClientHandler = loggedUser.stream()
                .filter(clientHandler -> clientHandler.getName().equals(recipientUserName))
                .findFirst();
        maybeClientHandler.ifPresent(clientHandler -> clientHandler.sendMessage(outboundMessage));
    }

}
