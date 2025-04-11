#include <Servo.h>

#define PULSE_LOW 387
#define X_PULSE_HIGH 2400
#define Y_PULSE_HIGH 2300

#define X_ANGLE_LOW 0
#define X_ANGLE_HIGH 180 

#define Y_ANGLE_LOW 0
#define Y_ANGLE_HIGH 128

Servo servoX;
Servo servoY;


void setup() {
  Serial.begin(9600);

  servoX.attach(D3, PULSE_LOW, X_PULSE_HIGH);
  servoY.attach(D5, PULSE_LOW, Y_
PULSE_HIGH);

}


void loop() {
  static int pan, tilt, microX, microY;

  if (Serial.available()) {
    String input = Serial.readStringUntil('\n');
    int xIndex = input.indexOf('X');
    int yIndex = input.indexOf('Y');
    if (xIndex >= 0 && yIndex > xIndex) {
      pan = input.substring(xIndex + 1, yIndex).toInt();
      tilt = input.substring(yIndex + 1).toInt();
      microX = map(pan, X_ANGLE_LOW, X_ANGLE_HIGH, PULSE_LOW, X_PULSE_HIGH);
      microY = map(tilt, Y_ANGLE_LOW, Y_ANGLE_HIGH, PULSE_LOW< Y_PULSE_HIGH);
      servoX.writeMicroseconds(microX);
      servoY.writeMicroseconds(microY);
      delay(50);
    } 
  }
}
