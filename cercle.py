import math

PRECISION = 5  # Decimal point precision

def circle_intersect(circle1, circle2):
    
    X1, Y1 = circle1[0], circle1[1]
    X2, Y2 = circle2[0], circle2[1]
    R1, R2 = circle1[2], circle2[2]

    Dx = X2-X1
    Dy = Y2-Y1
    
    chorddistance = (R1**2 - R2**2 + D**2)/(2*D)
    # distance from 1st circle's centre to the chord between intersects
    halfchordlength = math.sqrt(R1**2 - chorddistance**2)
    chordmidpointx = X1 + (chorddistance*Dx)/D
    chordmidpointy = Y1 + (chorddistance*Dy)/D
    I1 = (round(chordmidpointx + (halfchordlength*Dy)/D, PRECISION),
          round(chordmidpointy - (halfchordlength*Dx)/D, PRECISION))
    theta1 = round(math.degrees(math.atan2(I1[1]-Y1, I1[0]-X1)),
                   PRECISION)
    I2 = (round(chordmidpointx - (halfchordlength*Dy)/D, PRECISION),
          round(chordmidpointy + (halfchordlength*Dx)/D, PRECISION))
    theta2 = round(math.degrees(math.atan2(I2[1]-Y1, I2[0]-X1)),
                   PRECISION)
    if theta2 > theta1:
        I1, I2 = I2, I1
    return (I1, I2)
