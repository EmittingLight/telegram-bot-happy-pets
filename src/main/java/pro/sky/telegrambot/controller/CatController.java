package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Cat;
import pro.sky.telegrambot.service.CatService;

import java.util.Collection;

@RestController
@RequestMapping("cat")
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public Cat createCat(@RequestBody Cat cat) {
        return catService.createCat(cat);
    }

    @GetMapping("/{id}")
    public Cat getCatInfo(@PathVariable Long id) {
        return catService.findCat(id);
    }

    @PutMapping
    public Cat editCat(@RequestBody Cat cat) {
        return catService.editCat(cat);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id) {
        catService.deleteCat(id);
    }

    @GetMapping
    public Collection<Cat> getAllCatsOwner() {
        return catService.getAllCats();
    }
}
