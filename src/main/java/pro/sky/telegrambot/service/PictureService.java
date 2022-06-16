package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.Picture;
import pro.sky.telegrambot.model.UserCat;
import pro.sky.telegrambot.repository.PictureRepository;
import pro.sky.telegrambot.repository.UserCatRepository;
import pro.sky.telegrambot.repository.UserDogRepository;

import javax.transaction.Transactional;
import com.pengrad.telegrambot.model.File;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class PictureService {
    @Value("Picture")
    private String picturesDir;

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final PictureRepository pictureRepository;
    private final UserCatRepository userCatRepository;
    private final UserDogRepository userDogRepository;

    public PictureService(TelegramBot telegramBot, PictureRepository pictureRepository, UserCatRepository userCatRepository, UserDogRepository userDogRepository) {
        this.telegramBot = telegramBot;
        this.pictureRepository = pictureRepository;
        this.userCatRepository = userCatRepository;
        this.userDogRepository = userDogRepository;
    }

    public void uploadPicture(Long userCatId, byte[] pictureFile, File file) throws IOException {
        logger.info("Был вызван метод для загрузки фотографии  '{}'", userCatId);
        UserCat userCat = userCatRepository.getById(userCatId);
        Path filePath = Path.of(picturesDir, "pictures" + "." + getExtensions(Objects.requireNonNull(file.filePath())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        Picture picture = findPicture(userCatId);
        picture.setUserCat(userCat);
        picture.setFilePath(filePath.toString());
        picture.setFileSize(file.fileSize());
        picture.setData(pictureFile);
        pictureRepository.save(picture);
    }

    public Picture findPicture(Long userCatId) {
        logger.info("Был вызван метод для поиска фотографии '{}'", userCatId);
        return pictureRepository.findByUserCatId(userCatId).orElse(new Picture());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
