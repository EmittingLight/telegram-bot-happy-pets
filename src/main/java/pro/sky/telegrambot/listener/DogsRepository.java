package pro.sky.telegrambot.listener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogsRepository extends JpaRepository<DogsOwner,Long> {
}