package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.UserReport;
import pro.sky.telegrambot.service.UserReportService;

import java.util.Collection;

@RestController
@RequestMapping("user-report")
public class UserReportController {
    private final UserReportService userReportService;

    public UserReportController(UserReportService userReportService) {
        this.userReportService = userReportService;
    }

    @PostMapping
    public UserReport createUserReport(@RequestBody UserReport userReport) {
        return userReportService.createUserReport(userReport);
    }

    @GetMapping("/{id}")
    public UserReport getUserReportInfo(@PathVariable Long id) {
        return userReportService.findUserReport(id);
    }

    @PutMapping
    public UserReport editUserReport(@RequestBody UserReport userReport) {
        return userReportService.editUserReport(userReport);
    }

    @DeleteMapping("/{id}")
    public void deleteUserReport(@PathVariable Long id) {
        userReportService.deleteUserReport(id);
    }

    @GetMapping
    public Collection<UserReport> getAllUserReport() {
        return userReportService.getAllUsersReport();
    }
}
