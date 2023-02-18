package frc.robot.subsystems.communication;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.*;
import java.net.*;

/* Quick explanation of the code below
 * The constructor takes in the address and port to connect to
 * It then connects to the server
 * Once connected the server will start sending data to the client
 * 
 * Each packet sent from the server contains the id, dist, and angle
 * [1 ,2,3,4,5,6,7,8,9]
 * [id,dist 4b, ang 4b]
 * 
 * The id is one byte
 * Both dist & angle are floats, so they take up 4 bytes
 * 
 * The periodic function just constantly receives info and updates the array in Constants
*/

public class CommClient extends SubsystemBase
{
    /* Create our sockets  */ 
    private Socket m_socket = null;
    private DataInputStream m_input = null;

    private byte id;

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
	    TagData.TAG_DATA[id-1].dist = m_input.readFloat();

	    /* Grab the 2nd float value */
	    TagData.TAG_DATA[id-1].angle = m_input.readFloat();
	} catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {}
}