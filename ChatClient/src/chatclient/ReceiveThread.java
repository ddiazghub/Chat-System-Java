/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author david
 */
public class ReceiveThread extends Thread {
    private Socket socket;
    private ConcurrentLinkedQueue queue;
            
    public ReceiveThread(Socket socket, ConcurrentLinkedQueue queue) {
        this.socket = socket;
        this.queue = queue;
    }
    
    public void run() {
        try {
            DataInputStream in = new DataInputStream(this.socket.getInputStream());
        
            while (!this.socket.isClosed()) {
                this.queue.add("(Servidor): " + in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
