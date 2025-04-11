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

}

