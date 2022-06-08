package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.UserCat;
import pro.sky.telegrambot.model.UserDog;

import java.util.List;

public interface UserDogRepository extends JpaRepository<UserDog, Long> {
    List<UserDog> findAll();
}
