package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CatsOwnerService implements CatsDogsInterface{
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final CatsRepository catsRepository;
    private TelegramBot telegramBot;
    public CatsOwnerService(CatsRepository catsRepository, TelegramBot telegramBot){
        this.catsRepository=catsRepository;
        this.telegramBot=telegramBot;
    }
    @Override
    public SendResponse stepOne(Update update){
        InlineKeyboardMarkup keyboardForStepOne = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Как нас найти?");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Оформить пропуск на авто");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Техника безопасности");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Отправить свой контакт");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("расписание");
        button2.callbackData("авто");
        button3.callbackData("тб");
        button4.callbackData("сохранение");
        button5.callbackData("волонтер");
        keyboardForStepOne.addRow(button1);
        keyboardForStepOne.addRow(button2);
        keyboardForStepOne.addRow(button3);
        keyboardForStepOne.addRow(button4);
        keyboardForStepOne.addRow(button5);
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),"Что будем делать?").replyMarkup(keyboardForStepOne));
    }
}
