package pl.gbanasik;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

/*
This is the chat server program.
 */

public class ChatServer {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat Server is listening on port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add((newUser));
                newUser.start();
            }
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /*
    Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    /*
    Stores username of the newly connected client.
     */

    void addUserName(String userName) {
        userNames.add(userName);
    }

    /*
    When client is disconnected, removes the associated username and UserThread
     */
    void  removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user: " + userName + "left");
        }
    }

    Set<String> getUserNames() {
        return this.userNames;
    }

    /*
    Returns true of are other users connected (not count the currently connected user)
     */
    boolean hasUsers() {
        return  !this.userNames.isEmpty();
    }
    
    

    public static void main(String[] args) {
        ServerFrame serverFrame = new ServerFrame();
        ChatServer server = new ChatServer(3333);
        server.execute();

    }
}
