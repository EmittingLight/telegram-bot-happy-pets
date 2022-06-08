package pro.sky.telegrambot.model;


import javax.persistence.*;
import java.util.Objects;


@Entity
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reportText;
    private Long chatId;

    public UserReport(Integer id, String reportText, Long chatId) {
        this.id = id;
        this.reportText = reportText;
        this.chatId = chatId;
    }

    public UserReport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReport that = (UserReport) o;
        return Objects.equals(id, that.id) && Objects.equals(reportText, that.reportText) && Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportText, chatId);
    }
}
