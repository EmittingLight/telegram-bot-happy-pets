package pro.sky.telegrambot.controller;

import com.pengrad.telegrambot.model.File;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.model.Picture;
import pro.sky.telegrambot.service.PictureService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("picture")
public class PictureController {
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping(value = "/{userCatId}/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long userCatId, @RequestParam MultipartFile picture, @RequestParam File file) throws IOException {
        pictureService.uploadPicture(userCatId, picture.getBytes(), file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/from-db")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Picture picture = pictureService.findPicture(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(picture.getMediaType()));
        headers.setContentLength(picture.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(picture.getData());
    }

    @GetMapping(value = "/{id}/from-file")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Picture picture = pictureService.findPicture(id);
        Path path = Path.of(picture.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(picture.getMediaType());
            response.setContentLength((int) picture.getFileSize());
            is.transferTo(os);
        }
    }
}