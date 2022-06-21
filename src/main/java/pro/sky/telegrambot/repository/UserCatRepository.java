package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.UserCat;

import java.util.Optional;

public interface UserCatRepository extends JpaRepository<UserCat, Long> {

    Optional<UserCat> findByChatId(Long chatId);


}
