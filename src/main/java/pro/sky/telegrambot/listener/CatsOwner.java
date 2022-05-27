package pro.sky.telegrambot.listener;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class CatsOwner {

    private String catsOwnerName;
    private Long chatId;

    @Id
    @GeneratedValue
    private Long id;

    public CatsOwner( Long chatId, String catsOwnerName) {
        this.catsOwnerName = catsOwnerName;
        this.chatId = chatId;
    }

    public CatsOwner() {

    }

    public String getOwnerName() {
        return catsOwnerName;
    }

    public void setOwnerName(String catsOwnerName) {
        this.catsOwnerName = catsOwnerName;
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
        return Objects.equals(catsOwnerName, catsOwner.catsOwnerName) && Objects.equals(chatId, catsOwner.chatId) && Objects.equals(id, catsOwner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catsOwnerName, chatId, id);
    }

    @Override
    public String toString() {
        return "CatsOwner{" +
                "ownerName='" + catsOwnerName + '\'' +
                ", chatId=" + chatId +
                ", id=" + id +
                '}';
    }
}
