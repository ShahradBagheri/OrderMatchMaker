package com.example.maktabproject.util;

import com.example.maktabproject.exception.ImageToBigException;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ImageProcessing {

    public byte[] imageToBytes(String filepath) throws ImageToBigException {
        try{
            if(validImageSize(filepath))
                return Files.readAllBytes(Paths.get(filepath));
            else
                throw new ImageToBigException();
        }catch (IOException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void bytesToImage(byte[] imageData){
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

    public boolean validImageSize(String filepath){
        long sizeLimit = 300 * 1024;
        File file = new File(filepath);

        return  file.length() <= sizeLimit;
    }
}