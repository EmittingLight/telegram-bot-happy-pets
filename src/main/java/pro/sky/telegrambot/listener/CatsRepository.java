package pro.sky.telegrambot.listener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatsRepository extends JpaRepository<CatsOwner,Long> {
}
