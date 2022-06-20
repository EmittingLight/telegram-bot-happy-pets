package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.Picture;
import pro.sky.telegrambot.model.UserCat;
import pro.sky.telegrambot.repository.PictureRepository;
import pro.sky.telegrambot.repository.UserCatRepository;
import pro.sky.telegrambot.repository.UserDogRepository;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;

import com.pengrad.telegrambot.model.File;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

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

    /**
     * метод по загрузке фото принимает в качестве параметра файл и вызывает метод репозитория по сохранению в БД
     * @param chatId
     * @param pictureFile
     * @param file
     * @throws IOException
     */
    public void uploadPicture(Long chatId, byte[] pictureFile, File file) throws IOException {
        logger.info("Был вызван метод для загрузки фотографии  '{}'", chatId);
        Path filePath = Path.of(picturesDir, "pictures" + "." + getExtensions(Objects.requireNonNull(file.filePath())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        Picture picture = findPicture(chatId);
        picture.setFilePath(filePath.toString());
        picture.setFileSize(file.fileSize());
        picture.setData(pictureFile);
        pictureRepository.save(picture);
    }

    /**
     * метод поиска фото , принимает в качестве параметра chatId и вызывает метод репозитория по поиску из БД
     * @param chatId
     * @return
     */
    public Picture findPicture(Long chatId) {
        try {
            logger.info("Был вызван метод для поиска фотографии '{}'",chatId );
            return  pictureRepository.findByUserCatId(chatId).orElse(new Picture());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}

