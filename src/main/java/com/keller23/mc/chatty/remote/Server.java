package com.keller23.mc.chatty.remote;

import com.keller23.mc.chatty.Refs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(Refs.REMOTE_PORT.getInt());
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;

            ServerProtocol sp = new ServerProtocol();
            outputLine = sp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = sp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


