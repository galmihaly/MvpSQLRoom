package hu.unideb.inf.mvpsqlroom.databases.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.Card;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.User;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.UserCard;

@Database(entities = {User.class, Card.class, UserCard.class}, version = 1, exportSchema = false)
public abstract class Room extends RoomDatabase {

    private static Room INSTANCE;

    public static Room getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = androidx.room.Room.databaseBuilder(context.getApplicationContext(), Room.class, "Persons")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static Room getInstance() {
        if(INSTANCE == null) return null;

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract UserCardDAO userCardDAO();
    public abstract UsersDAO usersDAO();
    public abstract CardsDAO cardsDAO();
}
