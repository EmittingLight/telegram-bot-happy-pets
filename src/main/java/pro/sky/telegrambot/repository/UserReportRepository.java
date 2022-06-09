package pro.sky.telegrambot.repository;

import com.pengrad.telegrambot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.UserDog;
import pro.sky.telegrambot.model.UserReport;

import java.util.Collection;
import java.util.List;


public interface UserReportRepository extends JpaRepository<UserReport, Long> {
    List<UserReport> findAll();
}
