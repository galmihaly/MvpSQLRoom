package hu.unideb.inf.mvpsqlroom.databases.sqldatabase;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserCard {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private int userId;
    private int cardId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "UserCards{" +
                "userId=" + userId +
                ", cardId=" + cardId +
                '}';
    }
}
