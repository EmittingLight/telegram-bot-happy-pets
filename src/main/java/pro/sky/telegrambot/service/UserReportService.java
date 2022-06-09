package pro.sky.telegrambot.service;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.UserDog;
import pro.sky.telegrambot.model.UserReport;
import pro.sky.telegrambot.repository.UserReportRepository;

import java.util.Collection;
import java.util.List;

@Service
public class UserReportService {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final UserReportRepository userReportRepository;

    public UserReportService(TelegramBot telegramBot, UserReportRepository userReportRepository) {
        this.telegramBot = telegramBot;
        this.userReportRepository = userReportRepository;
    }

    public void saveReport(Message message) {
        UserReport userReport = new UserReport();
        String text = message.photo()[0].fileId();
        userReport.setReportText(text);
        Integer id = message.photo()[0].fileSize();
        userReport.setId(id);
        logger.info("Saving report {}", userReport);
    }
    public UserReport createUserReport(UserReport userReport) {
        return userReportRepository.save(userReport);
    }

    public UserReport findUserReport(Long id) {
        return userReportRepository.findById(id).get();
    }

    public UserReport editUserReport(UserReport userReport) {
        return userReportRepository.save(userReport);
    }

    public void deleteUserReport(Long id) {
        userReportRepository.deleteById(id);
    }

    public Collection<UserReport> getAllUsersReport() {
        return userReportRepository.findAll();
    }
}
