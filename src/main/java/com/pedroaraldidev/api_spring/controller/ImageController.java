package com.pedroaraldidev.api_spring.controller;

import com.pedroaraldidev.api_spring.dto.ImageRequestDTO;
import com.pedroaraldidev.api_spring.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/generate-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> generateImage(@ModelAttribute ImageRequestDTO requestDTO) throws IOException {
        if (requestDTO.getText() == null || requestDTO.getText().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O campo 'text' é obrigatório.".getBytes());
        }

        byte[] imageBytes = imageService.generateImageWithStyle(
                requestDTO.getText(),
                requestDTO.getBackgroundImage(),
                requestDTO.getTextColor(),
                requestDTO.getTextPosition(),
                requestDTO.getTextType(),
                requestDTO.isDarkenImage(),
                requestDTO.getWidth(),
                requestDTO.getHeight());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(imageBytes.length);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
