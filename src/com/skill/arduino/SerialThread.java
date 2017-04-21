package com.skill.arduino;

import java.io.BufferedReader;
import java.io.OutputStream;

/**
 * Created by Jeimison on 19/04/2017.
 */
public class SerialThread extends Thread {
    public SerialClass serial;
    BufferedReader input;
    OutputStream output;

    public SerialThread(int DATA_RT)
    {
        serial.DATA_RATE=DATA_RT;
        serial = new SerialClass();
        input = SerialClass.input;
        output = SerialClass.output;
    }

    @Override
    public void run()
    {
        try {
        serial.initialize();
            System.out.println("SERIAL INICIADO");
        //Fica preso em loop para funcionar
        while (!this.isInterrupted()){

        }
        //Finaliza
        serial.close();
            System.out.println("SERIAL FINALIZADO");
        }
        catch(Exception e){

        }

    }
}
