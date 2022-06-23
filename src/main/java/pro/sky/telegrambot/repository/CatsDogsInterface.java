package pro.sky.telegrambot.repository;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

public interface CatsDogsInterface {
    void getMenu(Update update);

    void stepOne(Update update);

    void sendAddress(Update update);

    void autoPass(Update update);

    void beSafe(Update update);

    void giveMeYourName(Update update);

    void saveUser(Message message);

    void volunteer(Update update);

    void stepTwo(Update update);

    void docs(Update update);

    void transport(Update update);

    void home(Update update);

    void refusal(Update update);

    void stepThree(Update update);
}
