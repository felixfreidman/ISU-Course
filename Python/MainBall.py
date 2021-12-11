import cv2
import numpy as np
import time

position = []

cam = cv2.VideoCapture(0)
cv2.namedWindow("Camera")


lowerRed = (3, 17, 51)
upperRed = (4, 255, 255)
lowerYellow = (20, 96, 128)
upperYellow = (30, 242, 255)
lowerOrange = (10, 10, 180)
upperOrange = (12, 255, 255)
lowerBlue = (82, 41, 20)
upperBlue = (110, 255, 255)
lowerGreen = (30, 102, 51)
upperGreen = (80, 255, 217)

while cam.isOpened():
    ret, image = cam.read()
    blurred = cv2.GaussianBlur(image, (11, 11), 0)
    hsv = cv2.cvtColor(blurred, cv2.COLOR_BGR2HSV)

    # mask = cv2.inRange(hsv, lowerYellow, upperYellow)
    # mask = cv2.inRange(hsv, lowerOrange, upperOrange)
    # mask = cv2.inRange(hsv, lowerBlue, upperBlue)
    # mask = cv2.inRange(hsv, lowerRed, upperRed)
    mask = cv2.inRange(hsv, lowerGreen, upperGreen)
    mask = cv2.erode(mask, None, iterations=2)
    mask = cv2.dilate(mask, None, iterations=2)

    cnts = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL,
                            cv2.CHAIN_APPROX_SIMPLE)[-2]

    if len(cnts) > 0:
        c = max(cnts, key=cv2.contourArea)
        (curr_x, curr_y), radius = cv2.minEnclosingCircle(c)
        if radius > 10:
            cv2.circle(image, (int(curr_x), int(curr_y)), 5,
                       (0, 255, 255), 2)
            cv2.circle(image, (int(curr_x), int(curr_y)), int(radius),
                       (0, 255, 255), 2)
            cv2.circle(image, (int(curr_x), int(curr_y)), 5,
                       (255, 0, 0), 2)
            cv2.circle(image, (int(curr_x), int(curr_y)), int(radius),
                       (255, 0, 0), 2)
            cv2.circle(image, (int(curr_x), int(curr_y)), 5,
                       (255, 165, 0), 2)
            cv2.circle(image, (int(curr_x), int(curr_y)), int(radius),
                       (255, 165, 0), 2)

    cv2.imshow("Camera", image)
    cv2.imshow("Mask", mask)
    key = cv2.waitKey(1)
    if key == ord('q'):
        break


cam.release()
cv2.destroyAllWindows()
