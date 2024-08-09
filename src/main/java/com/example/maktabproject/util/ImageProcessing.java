package com.example.maktabproject.util;

import com.example.maktabproject.exception.CustomExceptions;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Component
public class ImageProcessing {

    public byte[] imageToBytes(MultipartFile image) throws CustomExceptions.ImageToBigException {
        try {
            if (validImageSize(image.getSize()))
                return image.getBytes();
            else
                throw new CustomExceptions.ImageToBigException("image size is to big");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void bytesToImage(byte[] imageData) {
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

    public boolean validImageSize(long fileSize) {
        long sizeLimit = 300 * 1024;

        return fileSize <= sizeLimit;
    }
}