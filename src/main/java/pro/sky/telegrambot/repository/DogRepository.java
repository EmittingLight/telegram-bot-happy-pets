package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Dog;

public interface DogRepository extends JpaRepository<Dog,Long> {
}