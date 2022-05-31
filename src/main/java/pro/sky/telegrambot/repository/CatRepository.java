package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Cat;

public interface CatRepository extends JpaRepository<Cat,Long> {
}
