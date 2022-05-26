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
public class DogsOwnerService implements CatsDogsInterface{
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final DogsRepository dogsRepository;
    private TelegramBot telegramBot;
    public DogsOwnerService(DogsRepository dogsRepository, TelegramBot telegramBot){
        this.dogsRepository=dogsRepository;
        this.telegramBot=telegramBot;
    }

    @Override
    public SendResponse getMenu(Update update){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Информация о приюте");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Завести друга");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("инфа1");
        button2.callbackData("взять1");
        button3.callbackData("отчет1");
        button4.callbackData("волонтер1");
        keyboardMarkup.addRow(button1);
        keyboardMarkup.addRow(button2);
        keyboardMarkup.addRow(button3);
        keyboardMarkup.addRow(button4);
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),"Отлично!Чем могу помочь?").replyMarkup(keyboardMarkup));

    }
    @Override
    public SendResponse stepOne(Update update){
        InlineKeyboardMarkup keyboardForStepOne = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Как нас найти?");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Оформить пропуск на авто");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Техника безопасности");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Отправить свой контакт");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("расписание1");
        button2.callbackData("авто1");
        button3.callbackData("тб1");
        button4.callbackData("сохранение1");
        button5.callbackData("волонтер1");
        keyboardForStepOne.addRow(button1);
        keyboardForStepOne.addRow(button2);
        keyboardForStepOne.addRow(button3);
        keyboardForStepOne.addRow(button4);
        keyboardForStepOne.addRow(button5);
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),"Добро пожаловать в приют для собак! Что будем делать?").replyMarkup(keyboardForStepOne));
    }
}
