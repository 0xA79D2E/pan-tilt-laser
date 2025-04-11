package com.shygaan.pantilt.lasergui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.fazecast.jSerialComm.SerialPort;


import java.io.IOException;
import java.io.Serial;


public class Laser extends Application {
    private static final int PULSE_LOW = 387;
    private static final int X_PULSE_HIGH = 2400;
    private static final int Y_PULSE_HIGH = 2300;
    private static final int X_ANGLE_LOW = 0;
    private static final int X_ANGLE_HIGH = 180;
    private static final int Y_ANGLE_LOW = 0;
    private static final int Y_ANGLE_HIGH = 128;

    private int map(double value, double inMin, double inMax, double outMin, double outMax) {
        return (int) (outMin + (value - inMin) * (outMax - outMin) / (inMax - inMin));
    }

    @FXML
    private Pane pane;

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void handleClick(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        double angleX = X_ANGLE_HIGH - (x * X_ANGLE_HIGH / pane.getWidth());
        double angleY = (y * Y_ANGLE_HIGH / pane.getHeight());
        int microX = (int) (map(angleX, X_ANGLE_LOW, X_ANGLE_HIGH, PULSE_LOW, X_PULSE_HIGH));
        int microY = (int) (map(angleY, Y_ANGLE_LOW, Y_ANGLE_HIGH, PULSE_LOW, Y_PULSE_HIGH));
        System.out.println("X: " + angleX);
        System.out.println("Y: " + angleY);
        String command = String.format("X%.2fY%.2f\n", angleX, angleY);
        serialPort.sendData(command);
    }

    private int lastX = -1;
    private int lastY = -1;

    private long lastSendTime = 0;
    private static final long SEND_INTERVAL_MS = 50;


    @FXML
    public void handleDrag(MouseEvent mouseEvent) {
        long now = System.currentTimeMillis();
        if (now - lastSendTime < SEND_INTERVAL_MS) return;
        lastSendTime = now;

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        double angleX = X_ANGLE_HIGH - (x * X_ANGLE_HIGH / pane.getWidth());
        double angleY = (y * Y_ANGLE_HIGH / pane.getHeight());
        int microX = (int) (map(angleX, X_ANGLE_LOW, X_ANGLE_HIGH, PULSE_LOW, X_PULSE_HIGH));
        int microY = (int) (map(angleY, Y_ANGLE_LOW, Y_ANGLE_HIGH, PULSE_LOW, Y_PULSE_HIGH));
        System.out.println("X: " + angleX);
        System.out.println("Y: " + angleY);
        String command = String.format("X%.2fY%.2f\n", angleX, angleY);
        serialPort.sendData(command);
    }

    private SerialConnection serialPort = new SerialConnection();

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("laser.fxml"));
        loader.setController(this);
        primaryStage.setScene(new javafx.scene.Scene(loader.load()));
        primaryStage.setTitle("Laser Controller");
        primaryStage.show();


        boolean connected = serialPort.connect("/dev/ttyACM0");

        if (!connected) {
            System.out.println("Failed to connect to serial device.");
        }

    }
}
