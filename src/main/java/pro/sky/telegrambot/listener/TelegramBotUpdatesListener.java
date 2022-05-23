package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import javax.annotation.PostConstruct;

import java.util.List;

@Service
public class TelegramBotUpdatesListener extends TelegramLongPollingBot implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            if(message.text() != null && message.text().equals("/start")){
                getButtons(message);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    private void getButtons(Message message){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCats = new InlineKeyboardButton("\uD83D\uDC31Кошки");
        InlineKeyboardButton buttonDogs = new InlineKeyboardButton("\uD83D\uDC36Собаки");
        buttonCats.callbackData("коты");
        buttonDogs.callbackData("собаки");
        keyboardMarkup.addRow(buttonCats,buttonDogs);
        logger.info("Клавиатура создана");
        telegramBot.execute(new SendMessage(message.chat().id(), "Привет!Для начала выбери питомца!").replyMarkup(keyboardMarkup));
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