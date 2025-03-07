package com.pedroaraldidev.api_spring.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

@Service
public class ImageService {

    public byte[] generateImageWithStyle(String text, MultipartFile backgroundImageFile,
            String textColor, String textPosition, String textType, boolean darkenImage,
            Integer width, Integer height) throws IOException {

        BufferedImage backgroundImage;
        if (backgroundImageFile != null && !backgroundImageFile.isEmpty()) {
            backgroundImage = loadImageFromMultipartFile(backgroundImageFile);
        } else {
            backgroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = backgroundImage.createGraphics();
            graphics.setColor(Color.WHITE); // Fundo branco
            graphics.fillRect(0, 0, width, height);
            graphics.dispose();
        }

        if (darkenImage) {
            darkenImage(backgroundImage);
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        graphics.drawImage(backgroundImage, 0, 0, width, height, null);

        Color color = Color.decode(textColor);
        graphics.setColor(color);

        Font font;
        if ("subtitle".equalsIgnoreCase(textType)) {
            font = new Font("Arial", Font.PLAIN, 30); 
        } else {
            font = new Font("Arial", Font.BOLD, 50); 
        }
        graphics.setFont(font);


        Point textPositionPoint = calculateTextPosition(text, graphics, textPosition, width, height);

        graphics.drawString(text, textPositionPoint.x, textPositionPoint.y);

        graphics.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        return baos.toByteArray();
    }
    private BufferedImage loadImageFromMultipartFile(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            return ImageIO.read(inputStream);
        }
    }

    private void darkenImage(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int r = (int) (pixelColor.getRed() * 0.5);
                int g = (int) (pixelColor.getGreen() * 0.5);
                int b = (int) (pixelColor.getBlue() * 0.5);
                Color darkenedColor = new Color(r, g, b);
                image.setRGB(x, y, darkenedColor.getRGB());
            }
        }
    }

    private Point calculateTextPosition(String text, Graphics2D graphics, String position, int width, int height) {
        FontMetrics metrics = graphics.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        int x = 0;
        int y = 0;

        switch (position.toLowerCase()) {
            case "top":
                x = (width - textWidth) / 2; 
                y = 50; 
                break;
            case "bottom":
                x = (width - textWidth) / 2; 
                y = height - 50; 
                break;
            case "left":
                x = 50;
                y = (height + textHeight) / 2;
                break;
            case "right":
                x = width - textWidth - 50;
                y = (height + textHeight) / 2;
                break;
            case "center":
            default:
                x = (width - textWidth) / 2;
                y = (height + textHeight) / 2;
                break;
        }

        return new Point(x, y);
    }
}
