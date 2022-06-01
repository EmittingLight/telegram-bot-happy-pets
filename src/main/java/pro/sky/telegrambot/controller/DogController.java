package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Dog;
import pro.sky.telegrambot.service.DogService;

import java.util.Collection;

@RestController
@RequestMapping("dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    public Dog createDogsOwner(@RequestBody Dog dog) {
        return dogService.createDog(dog);
    }

    @GetMapping("/{id}")
    public Dog getDogsOwnerInfo(@PathVariable Long id) {
        return dogService.findDog(id);
    }

    @PutMapping
    public Dog editDogsOwner(@RequestBody Dog dog) {
        return dogService.editDog(dog);
    }

    @DeleteMapping("/{id}")
    public void deleteDogsOwner(@PathVariable Long id) {
        dogService.deleteDog(id);
    }

    @GetMapping
    public Collection<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

}
