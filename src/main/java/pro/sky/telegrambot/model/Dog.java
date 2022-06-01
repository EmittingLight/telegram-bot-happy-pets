package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class Dog {
    private String dogName;
    private Long chatId;

    @Id
    @GeneratedValue
    private Long id;

    public Dog() {

    }
    public Dog(Long chatId, String dogName) {
        this.dogName = dogName;
        this.chatId = chatId;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
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
        Dog dog = (Dog) o;
        return Objects.equals(dogName, dog.dogName) && Objects.equals(chatId, dog.chatId) && Objects.equals(id, dog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dogName, chatId, id);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                ", chatId=" + chatId +
                ", id=" + id +
                '}';
    }
}
