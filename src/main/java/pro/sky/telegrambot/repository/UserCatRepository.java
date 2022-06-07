package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.UserCat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserCatRepository extends JpaRepository<UserCat, Long> {

    List<UserCat> findAll();
}
