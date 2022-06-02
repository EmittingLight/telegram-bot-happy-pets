package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Owner {
    private String ownerName;
    private Long chatId;

    @Id
    @GeneratedValue
    private Long id;

    public Owner(String ownerName, Long chatId) {
        this.ownerName = ownerName;
        this.chatId = chatId;
    }

    public Owner() {
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
        Owner owner = (Owner) o;
        return Objects.equals(ownerName, owner.ownerName) && Objects.equals(chatId, owner.chatId) && Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerName, chatId, id);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerName='" + ownerName + '\'' +
                ", chatId=" + chatId +
                ", id=" + id +
                '}';
    }
}
