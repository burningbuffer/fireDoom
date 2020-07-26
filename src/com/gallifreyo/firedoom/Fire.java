package com.gallifreyo.firedoom;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Fire {


    //WIDTH/HEIGHT BOTH DIVIDED BY 10 (SIZE OF PIXEL)
    private int fireWidth = 100;
    private int fireHeight = 40;
    private  int palette = 36;

    private int[] firePixelsArray;

    private int c = 0;

    private static final Color[] colorArray = {new Color(7, 7, 7), new Color(31, 7, 7), new Color(47, 15, 7),
            new Color(71, 15, 7), new Color(87, 23, 7), new Color(103, 31, 7), new Color(119, 31, 7),
            new Color(143, 39, 7), new Color(159, 47, 7), new Color(175, 63, 7), new Color(191, 71, 7),
            new Color(199, 71, 7), new Color(223, 79, 7), new Color(223, 87, 7), new Color(223, 87, 7),
            new Color(215, 95, 7), new Color(215, 95, 7), new Color(215, 103, 15), new Color(207, 111, 15),
            new Color(207, 119, 15), new Color(207, 127, 15), new Color(207, 135, 23), new Color(199, 135, 23),
            new Color(199, 143, 23), new Color(199, 151, 31), new Color(191, 159, 31), new Color(191, 159, 31),
            new Color(191, 167, 39), new Color(191, 167, 39), new Color(191, 175, 47), new Color(183, 175, 47),
            new Color(183, 183, 47), new Color(183, 183, 55), new Color(207, 207, 111), new Color(223, 223, 159),
            new Color(239, 239, 199), new Color(255, 255, 255)};

    public Fire() {

        createDataStructure();
        //showFireStructure();
        createFireSource();
    }

    private void createDataStructure() {
        firePixelsArray = new int[fireWidth * fireHeight];
        for(int i = 0; i < firePixelsArray.length;i++) {
            firePixelsArray[i] = 0;
        }
    }

    private void showFireStructure() {

        for(int row = 0; row < fireHeight;row++) {
            System.out.println("");
            for(int column = 0; column < fireWidth;column++) {
                int indexPixel = column + (fireWidth * row);
                System.out.print(firePixelsArray[indexPixel]+"");
            }
        }


    }

    private void createFireSource() {
        for(int column = 0; column < fireWidth; column++) {
            int OverflowPixelIndex = fireWidth * fireHeight;
            int indexdPixel = (OverflowPixelIndex - fireWidth) + column;
            firePixelsArray[indexdPixel] = palette;
        }
    }

    private void calculateFirePropagation() {

        for(int column = 0; column < fireWidth; column++) {
            for(int row = 0;row < fireHeight; row++) {
                int pixelIndex = column + (fireWidth * row);
                updateFireIntensity(pixelIndex);
            }
        }

    }

    private void updateFireIntensity(int pixelIndex){
        Random r = new Random();

        int pixelIndexBelow = pixelIndex + fireWidth;

        if(pixelIndexBelow >= fireWidth * fireHeight) {
            return;
        }

        int decline = r.nextInt(3);

        int intensityPixelBelow = firePixelsArray[pixelIndexBelow];

        int newIntensity = intensityPixelBelow - decline >= 0 ? intensityPixelBelow - decline: 0;

        if((pixelIndex - decline) <= -1) {
            decline = 0;
        }

        firePixelsArray[pixelIndex - decline] = newIntensity;

    }

    public void update() {

        calculateFirePropagation();
        //showFireStructure();

    }

    public void render(Graphics g) {

        for(int x = 0; x < fireHeight; x++) {
            for(int y = 0; y < fireWidth; y ++) {
                int posX = y * 10;
                int posY = x * 10;

                g.setColor(colorArray[firePixelsArray[c]]);
                g.fillRect(posX, posY, 10, 10);

                c++;
            }
        }
        c = 0;
    }

}

