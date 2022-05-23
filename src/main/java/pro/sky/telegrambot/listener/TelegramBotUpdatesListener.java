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

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

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
            if(message!=null && message.text().equals(new String("/start"))){
                getButtons(message);
            }else{
                String data = update.callbackQuery().data();
                long chatId = update.callbackQuery().message().chat().id();
                if(Objects.equals(data, "коты")||Objects.equals(data, "псы")){
                    getMenu(update);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    private SendResponse getButtons(Message message){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCats = new InlineKeyboardButton("\uD83D\uDC31Кошки");
        InlineKeyboardButton buttonDogs = new InlineKeyboardButton("\uD83D\uDC36Собаки");
        buttonCats.callbackData("коты");
        buttonDogs.callbackData("собаки");
        keyboardMarkup.addRow(buttonCats,buttonDogs);
        logger.info("Клавиатура создана");
        return telegramBot.execute(new SendMessage(message.chat().id(),"Привет!Для начала выбери питомца!").replyMarkup(keyboardMarkup));
    }
    private SendResponse getMenu(Update update){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Информация о приюте");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Завести друга");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("инфа");
        button2.callbackData("взять");
        button3.callbackData("отчет");
        button4.callbackData("волонтер");
        keyboardMarkup.addRow(button1);
        keyboardMarkup.addRow(button2);
        keyboardMarkup.addRow(button3);
        keyboardMarkup.addRow(button4);
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),"Отлично!Чем могу помочь?").replyMarkup(keyboardMarkup));

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
