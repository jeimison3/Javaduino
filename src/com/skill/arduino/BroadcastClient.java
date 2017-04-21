package com.skill.arduino;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;

public class BroadcastClient
{

    private static int PORT = 11888;

    public BroadcastClient(int porta){
        this.PORT=porta;
    }

    public void enviaBroadcast(String mensagem) throws IOException {
        for(InetAddress HostBroad:Main.broads) {
            DatagramSocket dsock = new DatagramSocket();
            byte[] send = mensagem.getBytes("ASCII");
            DatagramPacket data = new DatagramPacket(send, send.length, HostBroad, this.PORT);
            dsock.send(data);
        }//Fim do loop foreach
        System.out.println("> "+Main.broads.size()+" broadcasts feitos.");
    }
}

class BroadcastServer extends Thread {
    private final int port;
    public String msgRead="";

    public BroadcastServer( int porta )
    {
        this.port = porta;
        this.setPriority(6);
        this.start();
    }

    @Override
    public void run()
    {
        try {
            DatagramSocket dsock = new DatagramSocket( port );
            DatagramPacket data;
            System.out.println("UDP INICIADO");
            while(!this.isInterrupted()) {
                data = new DatagramPacket( new byte[4096], 4096 );
                dsock.receive(data);
                System.out.println("UDP recebido.");
                msgRead=(new String( data.getData(), "ASCII" )).trim();
                Main.writeArduinoData(msgRead);
                System.out.println("[UDP RECEIVE] " + msgRead );
            }
            System.out.println("UDP FINALIZADO");
        } catch( SocketException ex ) {
            Logger.getLogger( BroadcastServer.class.getName() ).
                    log( Level.SEVERE, null, ex );
        } catch( IOException ex ) {
            Logger.getLogger( BroadcastServer.class.getName() ).
                    log( Level.SEVERE, null, ex );
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}