package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.UserDog;
import pro.sky.telegrambot.service.UserDogService;

import java.util.Collection;

@RestController
@RequestMapping("dog-user")
public class UserDogController {
    private final UserDogService userDogService;

    public UserDogController(UserDogService userDogService) {
        this.userDogService = userDogService;
    }

    /**
     * метод post принимает в качестве параметра userDog и вызывает метод сервиса по созданию объекта userDog в БД
     * @param userDog
     * @return
     */
    @PostMapping
    public UserDog createDogsUser(@RequestBody UserDog userDog) {
        return userDogService.createUserDog(userDog);
    }

    /**
     * метод get принимает в качестве параметра id и вызывает метод сервиса по извлечению обьекта userDog из БД
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserDog getDogsUserInfo(@PathVariable Long id) {
        return userDogService.findUserDog(id);
    }

    /**
     * метод put принимает в качестве параметра userDog и вызывает метод сервиса по редактированию в БД
     * @param userDog
     * @return
     */
    @PutMapping
    public UserDog editDogsUser(@RequestBody UserDog userDog) {
        return userDogService.editUserDog(userDog);
    }

    @DeleteMapping("/{id}")
    public void deleteDogsUser(@PathVariable Long id) {
        userDogService.deleteUserDog(id);
    }

    @GetMapping
    public Collection<UserDog> getAllDogsUser() {
        return userDogService.getAllUsersDog();
    }

}
