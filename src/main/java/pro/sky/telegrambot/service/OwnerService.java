package pro.sky.telegrambot.service;


import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.Owner;
import pro.sky.telegrambot.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;


@Service
public class OwnerService {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository, TelegramBot telegramBot) {
        this.ownerRepository = ownerRepository;
        this.telegramBot = telegramBot;
    }
    private final TelegramBot telegramBot;


    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner findOwner(Long id) {
        return ownerRepository.findById(id).get();
    }

    public Owner editOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
    public Collection<Owner> getAllOwner() {
        return ownerRepository.findAll();
    }
}

