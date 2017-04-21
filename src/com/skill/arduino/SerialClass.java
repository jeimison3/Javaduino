package com.skill.arduino;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
public class SerialClass implements SerialPortEventListener {

    public SerialPort serialPort;

    public static BufferedReader input;
    public static OutputStream output;
    /** Milliseconds to block while waiting for port open */
    public static final int TIME_OUT = 1000;
    /** Default bits per second for COM port. */
    public static int DATA_RATE = 250000;

    public void initialize() {
        CommPortIdentifier portId = Main.portSerial;

        if (portId == null) {
            System.err.println("ERRO: COM não encontrada.");
            return;
        }
        try {
// open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

// set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

// open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();
            char ch = 1;
            output.write(ch);

// add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine=input.readLine();
                Main.writeUDPData(inputLine);
                System.out.println("[ARDUINO OUT]: "+inputLine);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }

    }




}