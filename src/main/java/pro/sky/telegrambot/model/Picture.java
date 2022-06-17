package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private int fileSize;
    private String mediaType;
    private byte[] data;

    @OneToOne
    private UserCat userCat;

    @OneToOne
    private UserDog userDog;

    public UserCat getUserCat() {
        return userCat;
    }

    public void setUserCat(UserCat userCat) {
        this.userCat = userCat;
    }

    public UserDog getUserDog() {
        return userDog;
    }

    public void setUserDog(UserDog userDog) {
        this.userDog = userDog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return fileSize == picture.fileSize && Objects.equals(id, picture.id) && Objects.equals(filePath, picture.filePath) && Objects.equals(mediaType, picture.mediaType) && Arrays.equals(data, picture.data) && Objects.equals(userCat, picture.userCat) && Objects.equals(userDog, picture.userDog);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, userCat, userDog);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", userCat=" + userCat +
                ", userDog=" + userDog +
                '}';
    }
}