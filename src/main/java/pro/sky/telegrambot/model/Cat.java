package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class Cat {

    private String catName;
    private Long chatId;

    @Id
    @GeneratedValue
    private Long id;

    public Cat(Long chatId, String catName) {
        this.catName = catName;
        this.chatId = chatId;
    }

    public Cat() {

    }

    public String getName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
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
        Cat cat = (Cat) o;
        return Objects.equals(catName, cat.catName) && Objects.equals(chatId, cat.chatId) && Objects.equals(id, cat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catName, chatId, id);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "catName='" + catName + '\'' +
                ", chatId=" + chatId +
                ", id=" + id +
                '}';
    }
}
