package pro.sky.telegrambot.listener;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class DogsOwner {
    private String ownerName;
    private int phoneNumber;
    private Long chatId;

    @Id
    @GeneratedValue
    private Long id;
    public DogsOwner(){}

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
        DogsOwner dogsOwner = (DogsOwner) o;
        return phoneNumber == dogsOwner.phoneNumber && Objects.equals(ownerName, dogsOwner.ownerName) && Objects.equals(chatId, dogsOwner.chatId) && Objects.equals(id, dogsOwner.id);
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
