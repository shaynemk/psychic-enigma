package com.keller23.mc.chatty.KnockKnock;

/*
 * Copyright (c) 1995, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.keller23.mc.chatty.Refs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class KnockKnockServer implements Runnable {

    private static int SERVERPORT = -1;

    public static void main(String args[]) throws IOException {
        if(args.length == 1) {
            SERVERPORT = Integer.parseInt(args[0]);
        }
        (new Thread(new KnockKnockServer())).start();
    }

    public static void main() throws IOException {
        (new Thread(new KnockKnockServer())).start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        if(SERVERPORT == -1) {
            SERVERPORT = Refs.REMOTE_PORT.getInt();
        }

        if (SERVERPORT <= 1024) {
            System.err.println("Usage: java KnockKnockServer <SERVERPORT>\nPort must be greater than 1024.");
            //System.exit(1);
        } else {

            System.out.println("KnockKnockServer starting on SERVERPORT " + SERVERPORT);

            //int portNumber = Integer.parseInt(args[0]);
            while (Refs.REMOTE_ENABLED.getBoolean()) {
                try {
                    ServerSocket serverSocket = new ServerSocket(SERVERPORT);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out =
                            new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));


                    String inputLine, outputLine;

                    // Initiate conversation with client
                    KnockKnockProtocol kkp = new KnockKnockProtocol();
                    outputLine = kkp.processInput(null);
                    out.println(outputLine);

                    while ((inputLine = in.readLine()) != null) {
                        outputLine = kkp.processInput(inputLine);
                        out.println(outputLine);
                        if (outputLine.equals("Bye."))
                            break;
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on SERVERPORT "
                            + SERVERPORT + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}