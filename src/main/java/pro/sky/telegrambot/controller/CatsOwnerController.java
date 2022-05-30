package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.listener.CatsOwner;
import pro.sky.telegrambot.listener.CatsOwnerService;

import java.util.Collection;

@RestController
@RequestMapping("cats-owner")
public class CatsOwnerController {
    private final CatsOwnerService catsOwnerService;

    public CatsOwnerController(CatsOwnerService catsOwnerService) {
        this.catsOwnerService = catsOwnerService;
    }
    @PostMapping
    public CatsOwner createCatsOwner(@RequestBody CatsOwner catsOwner) {
        return catsOwnerService.createCatsOwner(catsOwner);
    }

    @GetMapping("/{id}")
    public CatsOwner getCatsOwnerInfo(@PathVariable Long id) {
        return catsOwnerService.findCatsOwner(id);
    }

    @PutMapping
    public CatsOwner editCatsOwner(@RequestBody CatsOwner catsOwner) {
        return catsOwnerService.editCatsOwner(catsOwner);
    }

    @DeleteMapping("/{id}")
    public void deleteCatsOwner(@PathVariable Long id) {
        catsOwnerService.deleteCatsOwner(id);
    }
    @GetMapping
    public Collection<CatsOwner> getAllCatsOwner() {
        return catsOwnerService.getAllCatsOwner();
    }
}
