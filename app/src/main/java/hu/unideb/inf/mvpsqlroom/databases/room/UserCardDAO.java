package hu.unideb.inf.mvpsqlroom.databases.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.UserCard;

@Dao
public interface UserCardDAO {

    @Transaction
    @Query("SELECT * FROM UserCard")
    List<UserCard> getAllUserCards() throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllUserCard(List<UserCard> userCardList) throws Exception;

    @Query("Delete from UserCard")
    void truncateUserCardTable();
}
