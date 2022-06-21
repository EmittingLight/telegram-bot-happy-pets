package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import pro.sky.telegrambot.model.UserCat;
import pro.sky.telegrambot.model.UserDog;
import pro.sky.telegrambot.repository.UserCatRepository;
import pro.sky.telegrambot.repository.UserDogRepository;
import pro.sky.telegrambot.service.PictureService;
import pro.sky.telegrambot.service.UserCatService;
import pro.sky.telegrambot.service.UserDogService;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@Component
public class TelegramBotUpdatesListener extends TelegramLongPollingBot implements UpdatesListener {

    private boolean isCat;

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    private final UserCatService userCatService;
    private final UserDogService userDogService;

    private final UserCatRepository userCatRepository;
    private final UserDogRepository userDogRepository;
    private final PictureService pictureService;


    public TelegramBotUpdatesListener(UserCatService userCatService, UserDogService userDogService, UserCatRepository userCatRepository, UserDogRepository userDogRepository, PictureService pictureService) {
        this.userCatService = userCatService;
        this.userDogService = userDogService;
        this.userCatRepository = userCatRepository;
        this.userDogRepository = userDogRepository;
        this.pictureService = pictureService;


    }

    @Override
    /**
     * main метод телеграмм бота
     */
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Processing update: {}", update);
                Message message = update.message();
                if (message != null && message.photo() == null && message.text().equals("/start")) {
                    getButtons(message);
                } else if (update.callbackQuery() != null) {
                    extracted(update);
                } else if (message != null && message.caption() != null && message.photo() != null) {
                    GetFile getFileRequest = new GetFile(message.photo()[0].fileId());
                    GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
                    try {
                        File file = getFileResponse.file();
                        byte[] fileContent = telegramBot.getFileContent(file);
                        //telegramBot.execute(new SendMessage(update.message().chat().id(), "Супер! Мы видим, что питомцу живется хорошо!"));
                        pictureService.uploadPicture(update.message().chat().id(), fileContent, file, isCat);
                    } catch (IOException e) {
                        logger.error("something went wrong");

                    }

                } else {
                    userCatService.saveUser(message);
                    userDogService.saveUser(message);
                }
            });
        } catch (NullPointerException e) {
            logger.error("Заполните отчет правильно");
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    /**
     * метод обработки нажатий кнопок меню в зависимости от того какая кнопка была нажата вызывается метод сервисов
     */
    private void extracted(Update update) {
        String data = update.callbackQuery().data();
        switch (data) {
            case ("коты"):
                isCat = true;
                userCatService.getMenu(update);
                break;
            case ("инфа0"):
                userCatService.stepOne(update);
                break;
            case ("расписание0"):
                userCatService.sendAddress(update);
                break;
            case ("авто0"):
                userCatService.autoPass(update);
                break;
            case ("тб0"):
                userCatService.beSafe(update);
                break;
            case ("взять0"):
                userCatService.stepTwo(update);
                break;
            case ("документы0"):
                userCatService.docs(update);
                break;
            case ("транспортировка0"):
                userCatService.transport(update);
                break;
            case ("обустройство0"):
                userCatService.home(update);
                break;
            case ("отказ0"):
                userCatService.refusal(update);
                break;
            case ("сохранение0"):
                userCatService.giveMeYourName(update);
                break;
            case ("волонтер0"):
                userCatService.volunteer(update);
                break;
            case ("отчет0"):
                userCatService.stepThree(update);
                break;
            default:
                break;
        }

        String data2 = update.callbackQuery().data();
        switch (data2) {
            case ("псы"):
                isCat = false;
                userDogService.getMenu(update);
                break;
            case ("инфа1"):
                userDogService.stepOne(update);
                break;
            case ("расписание1"):
                userDogService.sendAddress(update);
                break;
            case ("авто1"):
                userDogService.autoPass(update);
                break;
            case ("тб1"):
                userDogService.beSafe(update);
                break;
            case ("взять1"):
                userDogService.stepTwo(update);
                break;
            case ("документы1"):
                userDogService.docs(update);
                break;
            case ("транспортировка1"):
                userDogService.transport(update);
                break;
            case ("обустройство1"):
                userDogService.home(update);
                break;
            case ("кинолог1"):
                userDogService.cynologist(update);
                break;
            case ("списокКинологов1"):
                userDogService.cynologistList(update);
                break;
            case ("отказ1"):
                userDogService.refusal(update);
                break;
            case ("сохранение1"):
                userDogService.giveMeYourName(update);
                break;
            case ("волонтер1"):
                userDogService.volunteer(update);
                break;
            case ("отчет1"):
                userDogService.stepThree(update);
                break;
            default:
                break;
        }
    }

    /**
     * метод возвращающий  кнопки первичного меню
     */
    private SendResponse getButtons(Message message) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCats = new InlineKeyboardButton("\uD83D\uDC31Кошки");
        InlineKeyboardButton buttonDogs = new InlineKeyboardButton("\uD83D\uDC36Собаки");
        buttonCats.callbackData("коты");
        buttonDogs.callbackData("псы");
        keyboardMarkup.addRow(buttonCats, buttonDogs);
        logger.info("Клавиатура создана");
        return telegramBot.execute(new SendMessage(message.chat().id(), "Привет!Для начала выбери питомца!").replyMarkup(keyboardMarkup));
    }

    @Scheduled(cron = "00 00 11 1-30 * ?")
    public void findOwner() {
        List<UserCat> cats = userCatRepository.findAll();
        List<UserDog> dogs = userDogRepository.findAll();
        for (UserCat userCat : cats) {
            if (userCat != null && userCat.getPet().equals("yes")) {
                telegramBot.execute(new SendMessage(userCat.getChatId(), "Для отчета просим прислать фото животного," +
                        " его рацион, общее самочувствие и информацию об изменении в поведении."));
            }
        }
        for (UserDog userDog : dogs) {
            if (userDog != null && userDog.getPet().equals("yes")) {
                telegramBot.execute(new SendMessage(userDog.getChatId(), "Для отчета просим прислать фото животного," +
                        " его рацион, общее самочувствие и информацию об изменении в поведении."));
            }
        }
    }


    @Scheduled(cron = "@daily")
    public void probation() {
        LocalDate currentDate = LocalDate.now();
        List<UserCat> catUsers = userCatRepository.findAll();
        List<UserDog> dogUsers = userDogRepository.findAll();
        logger.info("ищу");
        for (UserCat userCat : catUsers) {
            if (userCat != null && userCat.getDate().equals(currentDate) && userCat.getPet().equals("yes")) {
                telegramBot.execute(new SendMessage(userCat.getChatId(), "Супер! Прошло 30 дней, " +
                        "ты отлично заботишься о питомце.Поздравляем с окончанием испытательного срока!"));
            } else {
                telegramBot.execute(new SendMessage(userCat.getChatId(), "К сожалению, испытательный срок не пройден! Приходите к нам в приют и попробуем еще раз! "));
            }
        }
        for (UserDog userDog : dogUsers) {
            if (userDog != null && userDog.getDate().equals(currentDate) && userDog.getPet().equals("yes")) {
                telegramBot.execute(new SendMessage(userDog.getChatId(), "Супер! Прошло 30 дней," +
                        " ты отлично заботишься о питомце.Поздравляем с окончанием испытательного срока!"));
            } else {
                telegramBot.execute(new SendMessage(userDog.getChatId(), "К сожалению, испытательный срок не пройден! Приходите к нам в приют и попробуем еще раз! "));
            }
        }
    }


    @Override
    public void onUpdateReceived(org.telegram.telegrambots.meta.api.objects.Update update) {

    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }
}
