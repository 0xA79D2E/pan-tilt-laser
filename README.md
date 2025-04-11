# pan-tilt-laser
A 2-axis tracking laser turret controlled via mouse input in a JavaFX GUI.  
Currently tracks real-time cursor movement and sends servo commands to an Arduino-compatible microcontroller via serial communication.

## To-Do
- Implement computer vision for autonomous tracking
- Increase servo precision (via code or hardware)
- Design proper mounting system

## Parts List
- Arduino compatible microcontroller (I'm using an `STM32 F401RE` devboard)
- 2x MG996R 180° servos
- Laser diode
- Ample power supply (I'm repurposing a 5V 2A switching wall adapter)

## Technologies Used
- Java & JavaFX
- Arduino
- jSerialComm Java library 
