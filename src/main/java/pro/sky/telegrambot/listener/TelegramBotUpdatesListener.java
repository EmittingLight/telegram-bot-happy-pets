package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import pro.sky.telegrambot.service.CatService;
import pro.sky.telegrambot.service.DogService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener extends TelegramLongPollingBot implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    private final CatService catService;
    private final DogService dogService;
    public TelegramBotUpdatesListener(CatService catService, DogService dogService){
        this.catService = catService;
        this.dogService = dogService;
    }

    @Override
    /**
     * main метод телеграмм бота
     */
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            if(message!=null && message.text().equals(new String("/start"))){
                getButtons(message);
            }else if(update.callbackQuery()!=null) {
                extracted(update);
            }else{
                catService.saveUser(message);
                dogService.saveUser(message);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * метод обработки нажатий кнопок меню в зависимости от того какая кнопка была нажата вызывается метод сервисов
     *
     */
    private void extracted(Update update) {
        String data = update.callbackQuery().data();
        switch(data){
            case("коты"):
                catService.getMenu(update);
                break;
            case("инфа0"):
                catService.stepOne(update);
                break;
            case("расписание0"):
                catService.sendAddress(update);
                break;
            case ("авто0"):
                catService.autoPass(update);
                break;
            case ("тб0"):
                catService.beSafe(update);
                break;
            case("взять0"):
                catService.stepTwo(update);
                break;
            case("документы0"):
                catService.docs(update);
                break;
            case ("транспортировка0"):
                catService.transport(update);
                break;
            case ("обустройство0"):
                catService.home(update);
                break;
            case("отказ0"):
                catService.refusal(update);
                break;
            case("сохранение0"):
                catService.giveMeYourName(update);
                break;
            case ("волонтер0"):
                catService.volunteer(update);
                break;
            default:
                break;
        }
        String data2 = update.callbackQuery().data();
        switch(data2){
            case("псы"):
                dogService.getMenu(update);
                break;
            case("инфа1"):
                dogService.stepOne(update);
                break;
            case("расписание1"):
                dogService.sendAddress(update);
                break;
            case ("авто1"):
                dogService.autoPass(update);
                break;
            case ("тб1"):
                dogService.beSafe(update);
                break;
            case("взять1"):
                dogService.stepTwo(update);
                break;
            case("документы1"):
                dogService.docs(update);
                break;
            case ("транспортировка1"):
                dogService.transport(update);
                break;
            case ("обустройство1"):
                dogService.home(update);
                break;
            case ("кинолог1"):
                dogService.cynologist(update);
                break;
            case ("списокКинологов1"):
                dogService.cynologistList(update);
                break;
            case("отказ1"):
                dogService.refusal(update);
                break;
            case("сохранение1"):
                dogService.giveMeYourName(update);
                break;
            case ("волонтер1"):
                dogService.volunteer(update);
                break;
            default:
                break;
        }
    }

    /**
     * метод возвращающий  кнопки первичного меню
     *
     */
    private SendResponse getButtons(Message message){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCats = new InlineKeyboardButton("\uD83D\uDC31Кошки");
        InlineKeyboardButton buttonDogs = new InlineKeyboardButton("\uD83D\uDC36Собаки");
        buttonCats.callbackData("коты");
        buttonDogs.callbackData("псы");
        keyboardMarkup.addRow(buttonCats,buttonDogs);
        logger.info("Клавиатура создана");
        return telegramBot.execute(new SendMessage(message.chat().id(),"Привет!Для начала выбери питомца!").replyMarkup(keyboardMarkup));
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
