package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.UserDog;

import java.util.Optional;

public interface UserDogRepository extends JpaRepository<UserDog, Long> {
    Optional<UserDog> findByChatId(Long chatId);
}
