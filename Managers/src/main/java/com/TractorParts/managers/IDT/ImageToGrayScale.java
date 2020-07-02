package com.TractorParts.managers.IDT;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nataliya on 24.02.2017.
 */
public class ImageToGrayScale {
    public String inputPath;
    public String formatName;
    public String outputPath;

    public ImageToGrayScale(String inputPath, String formatName, String outputPath) throws IOException {
        this.formatName = formatName;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }
    public void toGrayScale(){
        File inputFile = new File (inputPath);
        BufferedImage img = null;
        try {
            img = ImageIO.read(inputFile);
            BufferedImage grayScaleImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            for (int i=0; i<img.getHeight(); i++){
                for (int j=0; j<img.getWidth(); j++){
                    //get RGB color on each pixel
                    Color c = new Color(img.getRGB(j, i));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                    int a = c.getAlpha();
                    //simple grayscale = (r + g + b)/3
                    int gr = (r + g + b)/3;
                    Color grColor = new Color(gr, gr, gr, a);
                    grayScaleImage.setRGB(j, i, grColor.getRGB());
                }
            }
            ImageIO.write(grayScaleImage, formatName, new File(outputPath));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
