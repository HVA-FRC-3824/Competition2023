package frc.robot.subsystems.communication;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.io.*;
import java.net.*;

/* Default connection type is TCP. 
 * TODO: Change to UDP!
*/

public class CommClient extends SubsystemBase
{
    /* Create our sockets  */ 
    private Socket m_socket = null;
    private DataInputStream m_input = null;

    private float  dist;
    private byte   id;
    private float  angle;

    /* Try to establish a connection */ 
    public CommClient(String address, int port)
    {
        connectClient(address, port);
    }

    public void connectClient(String address, int port)
    {
        try {
            m_socket = new Socket(address,port);
            
            /* Print on success */
            System.out.println("Successfully connect to " + address + " on port: " + port);

            m_input = new DataInputStream(m_socket.getInputStream());
	    System.out.println("DataInputStream connected to client. ");

        } catch (Exception i) {
            System.out.println(i);
        } 
    }

    public void receiveMessage()
    {
	try {
	    /* Read first byte of the packet, which is the id. */
	    this.id = m_input.readByte();

	    /* Grab the 1st float value */
	    this.dist = m_input.readFloat();

	    /* Grab the 2nd float value */
	    this.angle = m_input.readFloat();
	} catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMessage()
    {
 	    System.out.println(this.id);	
	    System.out.println(this.dist);
	    System.out.println(this.angle);
    }
}