package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CatsOwnerService implements CatsDogsInterface{
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final CatsRepository catsRepository;

    public CatsOwnerService(CatsRepository catsRepository, TelegramBot telegramBot) {
        this.catsRepository = catsRepository;
        this.telegramBot = telegramBot;
    }

    private TelegramBot telegramBot;

    @Override
    public SendResponse getMenu(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Информация о приюте");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Завести друга");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("инфа0");
        button2.callbackData("взять0");
        button3.callbackData("отчет0");
        button4.callbackData("волонтер0");
        keyboardMarkup.addRow(button1);
        keyboardMarkup.addRow(button2);
        keyboardMarkup.addRow(button3);
        keyboardMarkup.addRow(button4);

        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Отлично! Чем могу помочь?").replyMarkup(keyboardMarkup));

    }

    @Override
    public SendResponse stepOne(Update update) {
        InlineKeyboardMarkup keyboardMarkupForStepOne = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Как нас найти?");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Оформить пропуск на авто");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Техника безопасности");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Отправить свой контакт");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("расписание0");
        button2.callbackData("авто0");
        button3.callbackData("тб0");
        button4.callbackData("сохранение0");
        button5.callbackData("волонтер0");
        keyboardMarkupForStepOne.addRow(button1);
        keyboardMarkupForStepOne.addRow(button2);
        keyboardMarkupForStepOne.addRow(button3);
        keyboardMarkupForStepOne.addRow(button4);
        keyboardMarkupForStepOne.addRow(button5);

        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Добро пожаловать в кошачий приют!Что будем делать?").replyMarkup(keyboardMarkupForStepOne));
    }
    @Override
    public SendResponse stepTwo(Update update) {
        InlineKeyboardMarkup keyboardMarkupForStepTwo = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Список необходимых документов");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Рекомендации по транспортировке");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Обустройство дома для питомца");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Причины отказа");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Отправить контактные данные");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("документы0");
        button2.callbackData("транспортировка0");
        button3.callbackData("обустройство0");
        button4.callbackData("отказ0");
        button5.callbackData("сохранение0");
        button6.callbackData("волонтер0");
        keyboardMarkupForStepTwo.addRow(button1);
        keyboardMarkupForStepTwo.addRow(button2);
        keyboardMarkupForStepTwo.addRow(button3);
        keyboardMarkupForStepTwo.addRow(button4);
        keyboardMarkupForStepTwo.addRow(button5);
        keyboardMarkupForStepTwo.addRow(button6);

        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Привет! Здорово, что Вы решили завести нового друга! Прежде чем взять питомца к себе, рекомендуем Вам прийти и познакомиться с ним вживую в нашем приюте. А пока, давайте подготовимся к новому члену семьи. Что интересно?").replyMarkup(keyboardMarkupForStepTwo));
    }

    @Override
    public SendResponse sendAddress(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Наш адрес ул.Котиков, дом 6"));
    }

    @Override
    public SendResponse autoPass(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Чтобы получить пропуск для своего автомобиля, позвоните на пункт охраны по номеру 8*******"));
    }

    @Override
    public SendResponse beSafe(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Не тискайте котиков слишком много, иногда им это не нравится\uD83D\uDE3E"));
    }

    @Override
    public SendResponse giveMeYourName(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Пожалуйста, введите свои имя, фамилию и отчество в следующем виде : Иванов Иван Иванович"));
    }

    public void saveUser(Message message) {
        if (message != null) {
            Pattern pattern = Pattern.compile("([А-ЯЁ][а-яё]+[\\-\\s]?){3,}");
            Matcher matcher = pattern.matcher(message.text());
            logger.info("поделил");
            if (matcher.matches()) {
                String ownerName = matcher.group();
                CatsOwner object = new CatsOwner(message.chat().id(), ownerName);
                catsRepository.save(object);
                logger.info("сохранил");
            }
        }
    }
    @Override
    public SendResponse volunteer(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Ожидайте, Вам ответит освободившийся волонтер"));
    }
    @Override
    public SendResponse docs(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Для того, чтобы взять котенка, просим предоставить следующие документы: справка с места работы, отсутствие вирусных инфекций в будущем доме."));
    }
    @Override
    public SendResponse transport(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Когда будете забирать котенка, наличие переноски для кошек обязательно! Если Вы на своем авто, то ОБЯЗАТЕЛЬНО пристегните переноску ремнем безопасности."));
    }
    @Override
    public SendResponse home(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "В первый день не берите котенка на руки, пусть он сам осмотрит новый дом! Поставьте лоток и миску и покажите котенку, где будуд проходить кормления и туалет. Если кот уже взрослый, следите, чтобы другие питомцы в доме не причинили ему вреда, защищая свою территорию. Кот с ограниченными возможностями нуждается в большем внимании, постарайтесь быть рядом в первое время."));
    }
    @Override
    public SendResponse refusal(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Мы вправе отказать Вам в усыновлении питомца при отсутствии необходимых документов и неадекватном поведении в отношении к животному"));
    }
}
