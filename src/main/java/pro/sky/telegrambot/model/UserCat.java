package pro.sky.telegrambot.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class UserCat {

    private String userName;
    private Long chatId;
    private String pet;
    private LocalDate date;


    @Id
    @GeneratedValue
    private Long id;

    public UserCat() {

    }

    public UserCat(Long chatId, String userName, String pet, LocalDate date) {
        this.userName = userName;
        this.chatId = chatId;
        this.pet=pet;
        this.date=date;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCat userCat = (UserCat) o;
        return Objects.equals(userName, userCat.userName) && Objects.equals(chatId, userCat.chatId) && Objects.equals(pet, userCat.pet) && Objects.equals(date, userCat.date) && Objects.equals(id, userCat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, chatId, pet, date, id);
    }

    @Override
    public String toString() {
        return "UserCat{" +
                "userName='" + userName + '\'' +
                ", chatId=" + chatId +
                ", pet='" + pet + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
