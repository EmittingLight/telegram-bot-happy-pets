package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.UserCat;
import pro.sky.telegrambot.service.UserCatService;

import java.util.Collection;

@RestController
@RequestMapping("cat-user")
public class UserCatController {
    private final UserCatService userCatService;

    public UserCatController(UserCatService userCatService) {
        this.userCatService = userCatService;
    }

    @PostMapping
    public UserCat createCatsUser(@RequestBody UserCat userCat) {
        return userCatService.createUserCat(userCat);
    }

    @GetMapping("/{id}")
    public UserCat getCatsUserInfo(@PathVariable Long id) {
        return userCatService.findUserCat(id);
    }

    @PutMapping
    public UserCat editCatsUser(@RequestBody UserCat userCat) {
        return userCatService.editUserCat(userCat);
    }

    @DeleteMapping("/{id}")
    public void deleteCatsUser(@PathVariable Long id) {
        userCatService.deleteUserCat(id);
    }

    @GetMapping
    public Collection<UserCat> getAllCatsUser() {
        return userCatService.getAllUsersCat();
    }
}
