#include <SoftwareSerial.h>

const int RX_PIN = 10;
const int TX_PIN = 11;
const int RED_PIN = 6;
const int GREEN_PIN = 5;
const int BLUE_PIN = 3;

SoftwareSerial mySerial(RX_PIN, TX_PIN);

void setup() {
  pinMode(RX_PIN, INPUT);
  pinMode(TX_PIN, OUTPUT);
  pinMode(RED_PIN, OUTPUT);
  pinMode(BLUE_PIN, OUTPUT);
  pinMode(GREEN_PIN, OUTPUT);
  
  Serial.begin(9600);

  while (!Serial);

  mySerial.begin(9600);
  delay(100);
}

void loop() {
  
  if (Serial.available() >= 3) {

    uint8_t r = Serial.read();
    uint8_t g = Serial.read();
    uint8_t b = Serial.read();
    
    analogWrite(RED_PIN, r);
    analogWrite(GREEN_PIN, g);
    analogWrite(BLUE_PIN, b);

    while (Serial.available() > 0) {
      mySerial.write(Serial.read());
    } 
  }
  
  delay(100);
}