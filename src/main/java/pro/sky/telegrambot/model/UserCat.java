package pro.sky.telegrambot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class UserCat {

    private String userName;
    private Long chatId;
    private String pet;

    @Id
    @GeneratedValue
    private Long id;

    public UserCat() {

    }

    public UserCat(Long chatId, String userName, String pet) {
        this.userName = userName;
        this.chatId = chatId;
        this.pet=pet;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCat userCat = (UserCat) o;
        return Objects.equals(userName, userCat.userName) && Objects.equals(chatId, userCat.chatId) && Objects.equals(pet, userCat.pet) && Objects.equals(id, userCat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, chatId, pet, id);
    }

    @Override
    public String toString() {
        return "UserCat{" +
                "userName='" + userName + '\'' +
                ", chatId=" + chatId +
                ", pet='" + pet + '\'' +
                ", id=" + id +
                '}';
    }
}
