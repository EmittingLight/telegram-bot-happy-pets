package pro.sky.telegrambot.listener;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class CatsOwner {
    private String ownerName;
    private int phoneNumber;
    private Long chatId;

    @Id
    @GeneratedValue
    private Long id;
    public CatsOwner(){}

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        CatsOwner catsOwner = (CatsOwner) o;
        return phoneNumber == catsOwner.phoneNumber && Objects.equals(ownerName, catsOwner.ownerName) && Objects.equals(chatId, catsOwner.chatId) && Objects.equals(id, catsOwner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerName, phoneNumber, chatId, id);
    }

    @Override
    public String toString() {
        return "CatsOwner{" +
                "ownerName='" + ownerName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", chatId=" + chatId +
                ", id=" + id +
                '}';
    }
}
