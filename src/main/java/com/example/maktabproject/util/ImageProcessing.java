package com.example.maktabproject.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageProcessing {

    public static byte[] imageToBytes(String filepath){
        try{
            return Files.readAllBytes(Paths.get(filepath));
        }catch (IOException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void bytesToImage(byte[] imageData){
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);

            BufferedImage image = ImageIO.read(inputStream);

            inputStream.close();

            File outputFile = new File("output_image.jpg");
            ImageIO.write(image, "jpg", outputFile);
            System.out.println("Image saved to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validImageSize(String filepath){
        long sizeLimit = 300 * 1024;
        File file = new File(filepath);

        return  file.length() > sizeLimit;
    }
}