#define PIN_PHOTO_SENSOR1 A0
#define PIN_PHOTO_SENSOR2 A1
#define PIN_PHOTO_SENSOR3 A2
#define PIN_PHOTO_SENSOR4 A3
#define PIN_PHOTO_SENSOR5 A4
#include <SoftwareSerial.h>

SoftwareSerial mySerial(10, 11); // RX, TX
void setup() { 
  Serial.begin(9600);
  mySerial.begin(4800);
}

void loop() { 
  Serial.print(analogRead(PIN_PHOTO_SENSOR1));
  Serial.print(";");
  Serial.print(analogRead(PIN_PHOTO_SENSOR2));
  Serial.print(";");  
  Serial.print(analogRead(PIN_PHOTO_SENSOR3));
  Serial.print(";");
  Serial.print(analogRead(PIN_PHOTO_SENSOR4));
  Serial.print(";"); 
  Serial.print(analogRead(PIN_PHOTO_SENSOR5));
  Serial.println(";"); 
  
  delay(100);
  
  if (mySerial.available()) {
    while (mySerial.available()) {
   		Serial.print(mySerial.read());
      	Serial.print(";");
  	}
    Serial.println(" ");
  }   
}
 