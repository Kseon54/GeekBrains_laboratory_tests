package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {
    public static void main(String[] args) {
        new Client();
    }

    private Socket socket;

    private final AtomicBoolean isAlive = new AtomicBoolean(true);

    private DataOutputStream out;
    private DataInputStream in;

    public Client() {
        init();
        communicate();
    }

    private void init() {
        try {
            Thread.sleep(1000);
            socket = new Socket("localhost", 8899);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void communicate() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        startListenIn();
        startListenOut();
    }

    private void startListenOut() {
        try {
            Scanner scanner = new Scanner(System.in);

            while (isAlive.get()) {
                System.out.println("Please input message...");
                String outboundMessage = scanner.nextLine();
                out.writeUTF(outboundMessage);
                if (outboundMessage.equals("-exit")) break;
            }
        } catch (IOException e) {
            if (!getIsAlive()) return;
            e.printStackTrace();
        }
    }

    private void startListenIn() {
        new Thread(() -> {
            try {
                while (getIsAlive()) {
                    String inboundMessage = in.readUTF();
                    if (inboundMessage.equals("-disconnect")) {
                        disconnection();
                    } else System.out.println(inboundMessage);
                }
            } catch (IOException e) {
                if (!getIsAlive()) return;
                e.printStackTrace();
            }
        })
                .start();
    }

    private void disconnection() {
        isAlive.set(false);
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("You are disconnected by the server");
        System.out.println("Client closing...");
        System.out.println("STATUS OK.");
        System.out.println("To complete the work, click enter");
    }

    public boolean getIsAlive() {
        return isAlive.get();
    }
}
