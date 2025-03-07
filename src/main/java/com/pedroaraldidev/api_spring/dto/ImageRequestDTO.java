package com.pedroaraldidev.api_spring.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageRequestDTO {
    private String text = "Default Text";
    private MultipartFile backgroundImage;
    private String textColor = "#000000"; 
    private String textPosition = "center";
    private String textType = "title"; 
    private boolean darkenImage = false; 
    private Integer width = 800; 
    private Integer height = 600; 


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipartFile getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(MultipartFile backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTextPosition() {
        return textPosition;
    }

    public void setTextPosition(String textPosition) {
        this.textPosition = textPosition;
    }

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public boolean isDarkenImage() {
        return darkenImage;
    }

    public void setDarkenImage(boolean darkenImage) {
        this.darkenImage = darkenImage;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
