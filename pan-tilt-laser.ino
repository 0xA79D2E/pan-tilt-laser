#include <Servo.h>
#define PULSE_LOW 387
#define PULSE_HIGH_X 2400
#define PULSE_HIGH_Y 2300

Servo servoX;
Servo servoY;


void setup() {
  Serial.begin(9600);

  servoX.attach(D3, PULSE_LOW, PULSE_HIGH_X);
  servoY.attach(D5, PULSE_LOW, PULSE_HIGH_Y);

}


void loop() {
  if (Serial.available()) {
    String input = Serial.readStringUntil('\n');
    int xIndex = input.indexOf('X');
    int yIndex = input.indexOf('Y');
    if (xIndex >= 0 && yIndex > xIndex) {
      int pan = input.substring(xIndex + 1, yIndex).toInt();
      int tilt = input.substring(yIndex + 1).toInt();
      servoX.write(pan);
      servoY.write(tilt);
      delay(50);
    } 
  }
}
