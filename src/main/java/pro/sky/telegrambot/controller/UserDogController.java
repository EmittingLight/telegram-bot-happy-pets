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

    @PostMapping
    public UserDog createDogsUser(@RequestBody UserDog userDog) {
        return userDogService.createUserDog(userDog);
    }

    @GetMapping("/{id}")
    public UserDog getDogsUserInfo(@PathVariable Long id) {
        return userDogService.findUserDog(id);
    }

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
