package com.shygaan.pantilt.lasergui;
import com.fazecast.jSerialComm.SerialPort;

public class SerialConnection {
    private SerialPort port;

    public boolean connect(String portName) {
        port = SerialPort.getCommPort(portName);
        port.setBaudRate(9600);
        return port.openPort();
    }

    public void sendData(String data) {
        if (port != null && port.isOpen()) {
            port.writeBytes(data.getBytes(), data.length());
        }
    }

    public void closePort() {
        if (port != null && port.isOpen()) {
            port.closePort();
        }
    }

}
