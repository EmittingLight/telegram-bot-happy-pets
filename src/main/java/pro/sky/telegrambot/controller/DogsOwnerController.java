package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.listener.DogsOwner;
import pro.sky.telegrambot.listener.DogsOwnerService;

import java.util.Collection;

@RestController
@RequestMapping("dogs-owner")
public class DogsOwnerController {
    private final DogsOwnerService dogsOwnerService;

    public DogsOwnerController(DogsOwnerService dogsOwnerService) {
        this.dogsOwnerService = dogsOwnerService;
    }

    @PostMapping
    public DogsOwner createDogsOwner(@RequestBody DogsOwner dogsOwner) {
        return dogsOwnerService.createDogsOwner(dogsOwner);
    }

    @GetMapping("/{id}")
    public DogsOwner getDogsOwnerInfo(@PathVariable Long id) {
        return dogsOwnerService.findDogsOwner(id);
    }

    @PutMapping
    public DogsOwner editDogsOwner(@RequestBody DogsOwner dogsOwner) {
        return dogsOwnerService.editDogsOwner(dogsOwner);
    }

    @DeleteMapping("/{id}")
    public void deleteDogsOwner(@PathVariable Long id) {
        dogsOwnerService.deleteDogsOwner(id);
    }

    @GetMapping
    public Collection<DogsOwner> getAllDogsOwner() {
        return dogsOwnerService.getAllDogsOwner();
    }

}
