package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    public static void main(String[] args) {
        new Server();
    }

    private ServerSocket socket;
    private Socket client;
    private final AtomicBoolean isAlive = new AtomicBoolean(true);

    private DataOutputStream out;
    private DataInputStream in;

    public Server() {
        init();
        communicate();
        System.out.println("Closing the connection...");
        System.out.println("Shutting down...");
        System.out.println("STATUS OK.");
    }

    private void init() {
        try {
            socket = new ServerSocket(8899);
            System.out.println("Socket created...");
            System.out.println("Waiting for a connection...");
            client = socket.accept();
            System.out.println("Client connected...");
            System.out.println(client);
            System.out.println("STATUS OK.");
        } catch (IOException e) {
            System.out.println("STATUS NOK.");
            e.printStackTrace();
        }
    }

    private void communicate() {
        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        startListenIn();
        startListenOut();

    }

    private void startListenOut() {
        try {
            Scanner scanner = new Scanner(System.in);

            while (getIsAlive()) {
                System.out.println("Please input message...");
                String outboundMessage = scanner.nextLine();
                out.writeUTF(outboundMessage);
                if (outboundMessage.equals("-disconnect")) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startListenIn() {
        new Thread(() -> {
            try {
                while (getIsAlive()) {
                    String inboundMessage = in.readUTF();
                    if (inboundMessage.equals("-exit")) {
                        exit();
                    } else System.out.println(inboundMessage);
                }
            } catch (IOException e) {
                if (!getIsAlive()) return;
                e.printStackTrace();
            }
        })
                .start();
    }

    private void disconnectionClients() {
        try {
            String outboundMessage;
            outboundMessage = "Server closing...";
            out.writeUTF(outboundMessage);
            outboundMessage = "-disconnect";
            out.writeUTF(outboundMessage);

            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        isAlive.set(false);
        disconnectionClients();
        System.out.println("Turning off the server");
        System.exit(0);
    }

    public boolean getIsAlive() {
        return isAlive.get();
    }
}
