## Pixycam
### What is it?
The Pixycam is a vision sensor that we use to track objects based on their color. 
It can be taught up to 7 distinct color signatures using the PixyMon desktop app. 
Once Pixy has learned a new color, it will recognize that color and send information
about the position (X and Y) and dimensions (width and height) to the RoboRio. This 
is useful for autonomous especially, when the robot has to interact with field elements
without the help of human drivers.

### Porting to the RoboRio
On the RoboRio, we use the SPI Port to interface with the Pixy. The Pixy was originally 
designed to connect to an Arduino, which has a slightly different SPI Port than the 
RoboRio does. This means that you can't port directly from Pixy to RoboRio. Instead, you 
must connect certain pins in the SPI Port to certain pins on the Pixy. If these are incorrectly
connected, then the code **will not work.**


### To Do
* Figure out what the magic numbers in lines 55 and 68 of PixySPI.java mean

Subsystem | Completed
--- | ---
Intake | I think so?
Lift | No (?)
Vision / Pixy | No
Motor | Probably not
Conveyor | No
