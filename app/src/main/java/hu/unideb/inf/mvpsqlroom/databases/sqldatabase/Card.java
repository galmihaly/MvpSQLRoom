package hu.unideb.inf.mvpsqlroom.databases.sqldatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cards_table")
public class Card {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String data;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", active=" + active +
                '}';
    }
}
