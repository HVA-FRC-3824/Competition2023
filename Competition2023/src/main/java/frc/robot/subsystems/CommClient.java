package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.*;
import java.net.*;

/* Default connection type is TCP. */


public class CommClient extends SubsystemBase{

    /* Create our sockets  */ 
    private Socket m_socket = null;
    private DataOutputStream m_recieve = null;
    
    String message = "hey from javar"; /* Test message */

    /* Try to establish a connection */
    public CommClient(String address, int port)
    {
        try {
            m_socket = new Socket(address,port);
            
            /* Print on success */
            System.out.println("Successfully connect to " + address + " on port: " + port);

            /* Init our streams */
            m_recieve = new DataOutputStream(m_socket.getOutputStream());
        } catch (Exception i) {
            System.out.println(i);
        } 
    }
}   
