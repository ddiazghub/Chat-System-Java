/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatserver;

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
    private final Socket socket;
    private final ConcurrentLinkedQueue queue;
            
    public ReceiveThread(Socket socket, ConcurrentLinkedQueue queue) {
        this.socket = socket;
        this.queue = queue;
    }
    
    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(this.socket.getInputStream());
        
            while (!this.socket.isClosed()) {
                this.queue.add("(Cliente): " + in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
