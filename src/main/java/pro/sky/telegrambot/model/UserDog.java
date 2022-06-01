package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class UserDog {
    private String userName;
    private Long chatId;

    @Id
    @GeneratedValue
    private Long id;

    public UserDog() {

    }
    public UserDog(Long chatId, String userName) {
        this.userName = userName;
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String dogName) {
        this.userName = dogName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDog userDog = (UserDog) o;
        return Objects.equals(userName, userDog.userName) && Objects.equals(chatId, userDog.chatId) && Objects.equals(id, userDog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, chatId, id);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + userName + '\'' +
                ", chatId=" + chatId +
                ", id=" + id +
                '}';
    }
}
