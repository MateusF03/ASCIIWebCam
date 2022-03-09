package me.mateus.asciiwebcam.frame;

import com.github.sarxos.webcam.WebcamImageTransformer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCIITransformer implements WebcamImageTransformer {

    private final char[] ASCII_CHARACTERS = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$".toCharArray();

    @Override
    public BufferedImage transform(BufferedImage image) {
        int oldRange = 255;
        int newRange = 65;
        int SIZE = 400;
        int resizeSize = 60;


        BufferedImage transformedImage = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        BufferedImage newImage = resize(image, resizeSize, resizeSize);
        Graphics2D g2 = transformedImage.createGraphics();
        int w = SIZE / newImage.getWidth();
        int h = SIZE / newImage.getHeight();
        g2.setFont(g2.getFont().deriveFont((float) w));
        for (int x = 0; x < newImage.getWidth(); x++) {
            for (int y = 0; y < newImage.getHeight(); y++) {
                int color = newImage.getRGB(x,y);
                int red = (color & 0xff0000) >> 16;
                int green = (color & 0xff00) >> 8;
                int blue = color & 0xff;
                int avg = (red + green + blue) / 3;

                int idx = (((avg) * newRange) / oldRange);
                char c = ASCII_CHARACTERS[idx];
                g2.drawString(String.valueOf(c),x*w,y*h);
            }
        }
        g2.dispose();
        return transformedImage;
    }

    private BufferedImage resize(BufferedImage image, int w, int h) {
        BufferedImage resized = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resized.createGraphics();
        g2.drawImage(image,0,0,w,h,null);
        g2.dispose();
        return resized;
    }
}
