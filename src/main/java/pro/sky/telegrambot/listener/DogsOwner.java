package pro.sky.telegrambot.listener;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class DogsOwner {
    private String dogsOwnerName;
    private Long chatId;

    @Id
    @GeneratedValue
    private Long id;

    public DogsOwner() {

    }
    public DogsOwner( Long chatId, String dogsOwnerName) {
        this.dogsOwnerName = dogsOwnerName;
        this.chatId = chatId;
    }

    public String getOwnerName() {
        return dogsOwnerName;
    }

    public void setOwnerName(String dogsOwnerName) {
        this.dogsOwnerName = dogsOwnerName;
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
        DogsOwner dogsOwner = (DogsOwner) o;
        return Objects.equals(dogsOwnerName, dogsOwner.dogsOwnerName) && Objects.equals(chatId, dogsOwner.chatId) && Objects.equals(id, dogsOwner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dogsOwnerName, chatId, id);
    }

    @Override
    public String toString() {
        return "DogsOwner{" +
                "ownerName='" + dogsOwnerName + '\'' +
                ", chatId=" + chatId +
                ", id=" + id +
                '}';
    }
}
