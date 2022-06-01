package pro.sky.telegrambot.service;

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
import pro.sky.telegrambot.repository.CatsDogsInterface;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.Dog;
import pro.sky.telegrambot.repository.DogRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DogService implements CatsDogsInterface {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository, TelegramBot telegramBot) {
        this.dogRepository = dogRepository;
        this.telegramBot = telegramBot;
    }

    private final TelegramBot telegramBot;

    @Override
    public void getMenu(Update update) {
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

        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Отлично! Чем могу помочь?").replyMarkup(keyboardMarkup));

    }

    @Override
    public void stepOne(Update update) {
        InlineKeyboardMarkup keyboardMarkupForStepOne = new InlineKeyboardMarkup();
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
        keyboardMarkupForStepOne.addRow(button1);
        keyboardMarkupForStepOne.addRow(button2);
        keyboardMarkupForStepOne.addRow(button3);
        keyboardMarkupForStepOne.addRow(button4);
        keyboardMarkupForStepOne.addRow(button5);

        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Добро пожаловать в собачий приют! Что будем делать?").replyMarkup(keyboardMarkupForStepOne));
    }

    @Override
    public void stepTwo(Update update) {
        InlineKeyboardMarkup keyboardMarkupForStepTwo = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Список необходимых документов");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Рекомендации по транспортировке");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Обустройство дома для питомца");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Советы кинолога");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Список кинологов");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Причины отказа");
        InlineKeyboardButton button7 = new InlineKeyboardButton("Отправить свой контакт");
        InlineKeyboardButton button8 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("документы1");
        button2.callbackData("транспортировка1");
        button3.callbackData("обустройство1");
        button4.callbackData("кинолог1");
        button5.callbackData("списокКинологов1");
        button6.callbackData("отказ1");
        button7.callbackData("сохранение1");
        button8.callbackData("волонтер1");
        keyboardMarkupForStepTwo.addRow(button1);
        keyboardMarkupForStepTwo.addRow(button2);
        keyboardMarkupForStepTwo.addRow(button3);
        keyboardMarkupForStepTwo.addRow(button4);
        keyboardMarkupForStepTwo.addRow(button5);
        keyboardMarkupForStepTwo.addRow(button6);
        keyboardMarkupForStepTwo.addRow(button7);
        keyboardMarkupForStepTwo.addRow(button8);

        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Привет! Здорово, что Вы решили завести нового друга! Прежде чем взять питомца к себе," +
                        " рекомендуем Вам прийти и познакомиться с ним вживую в нашем приюте." +
                        " А пока, давайте подготовимся к новому члену семьи. Что интересно?").replyMarkup(keyboardMarkupForStepTwo));
    }

    @Override
    public void sendAddress(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Наш адрес ул.Песиков, дом 6"));
    }

    @Override
    public void autoPass(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Чтобы получить пропуск для своего автомобиля, позвоните на пункт охраны по номеру 8*******"));
    }

    @Override
    public void beSafe(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "В целях Вашей безопасности в нашем приюте запрещено дразнить собак и замахиваться на них!" +
                        " Так Вы их пугаете, собака может ответить Вам недружественным поведением."));
    }

    @Override
    public void giveMeYourName(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Пожалуйста, введите свои имя, фамилию и отчество в следующем виде : Иванов Иван Иванович"));
    }

    @Override
    public void saveUser(Message message) {
        if (message != null) {
            Pattern pattern = Pattern.compile("([А-ЯЁ][а-яё]+[\\-\\s]?){3,}");
            Matcher matcher = pattern.matcher(message.text());
            logger.info("поделил");
            if (matcher.matches()) {
                String ownerName = matcher.group();
                Dog object = new Dog(message.chat().id(), ownerName);
                dogRepository.save(object);
                logger.info("сохранил");
            }
        }
    }

    @Override
    public void volunteer(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Ожидайте, Вам ответит освободившийся волонтер"));
    }

    @Override
    public void docs(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Для того, чтобы взять щенка, просим предоставить следующие документы: справка с места работы, " +
                        "отсутствие вирусных инфекций в будущем доме."));
    }

    @Override
    public void transport(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Когда будете забирать щенка, желательно делать это на личном автомобиле." +
                        " При себе иметь поводок, ошейник по размеру и намордник."));
    }

    @Override
    public void home(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Покажите щенку его миску и игрушки. Если пес уже взрослый, следите, " +
                        "чтобы другие питомцы в доме не причинили ему вреда, защищая свою территорию. " +
                        "Пес с ограниченными возможностями нуждается в большем внимании, постарайтесь быть рядом в первое время."));
    }

    @Override
    public void refusal(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Мы вправе отказать Вам в усыновлении питомца при отсутствии необходимых документов" +
                        " и неадекватном поведении в отношении к животному"));
    }

    public SendResponse cynologist(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Разговаривайте со своей собакой, следите за своими эмоциями,наблюдайте за выражением внутреннего" +
                        " состояния собаки и приводите его в норму, ставьте границы допустимого."));
    }

    public SendResponse cynologistList(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Позвоните нам по номеру 8******* и мы подберем для Вас проверенного кинолога"));
    }

    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog findDog(Long id) {
        return dogRepository.findById(id).get();
    }

    public Dog editDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }
}
