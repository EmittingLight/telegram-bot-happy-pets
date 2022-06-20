package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pro.sky.telegrambot.model.Picture;

import java.util.Optional;

public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {
    Optional<Picture> findByUserCatId(Long chatId);

}