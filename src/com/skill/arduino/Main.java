package com.skill.arduino;

import gnu.io.CommPortIdentifier;

import java.net.InetAddress;
import java.io.IOException;
import java.util.HashSet;


public class Main {

    static BroadcastServer brdServ;
    static BroadcastClient brdSend;
    public static SkillForm1 form1;
    static CommPortIdentifier portSerial;
    public static SerialThread serialThr;
    public static HashSet<InetAddress> broads;



    public static synchronized void writeArduinoData(String data) {
        System.out.println("[ARDUINO IN]: " + data);
        try {
            //Trecho seguinte responsável por finalizar o App remotamente.
            if(data.indexOf("[SERVER_CLOSE]")!=-1) {serialThr.interrupt();brdServ.interrupt();}
            Main.serialThr.serial.serialPort.getOutputStream().write(data.getBytes("ASCII"));
            //SerialClass.output.write(data.getBytes("ASCII"));
        } catch (Exception e) {
            System.out.println("Nao é possível escrever na porta.\nMsg:"+e.toString());
        }
    }

    public static void writeUDPData(String data) {
        System.out.println("[UDP BROADCAST]: " + data);
        try {
            brdSend.enviaBroadcast(data);
        } catch (Exception e) {
            System.out.println("Nao é possível enviar.\nMsg:"+e.toString());
        }
    }



    public static void main(String[] args) throws IOException {
        broads = Broadcasts.main();
        portSerial=SerialCOMGet.main();
        try
        {
            form1 = new SkillForm1();
            form1.start();
            brdServ = new BroadcastServer(11888); //RECEBE broadcasts

            brdSend = new BroadcastClient(11889); //ENVIA broadcasts

            if(portSerial!=null) {
                serialThr = new SerialThread(115200);
                serialThr.start();
            }else System.err.println("ERRO: NENHUMA PORTA SERIAL FOI ENCONTRADA.");

        }
        catch(Exception e){}



    }

}
