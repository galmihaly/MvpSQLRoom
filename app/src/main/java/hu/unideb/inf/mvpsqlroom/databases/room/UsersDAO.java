package hu.unideb.inf.mvpsqlroom.databases.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.User;

@Dao
public interface UsersDAO {

    @Query("SELECT * FROM users_table")
    List<User> getAllUser() throws Exception;

    @Transaction
    @Query("SELECT * FROM users_table U " +
            "INNER JOIN UserCard UC ON UC.userId = U.id " +
            "INNER JOIN cards_table C ON C.id = UC.cardId " +
            "WHERE UC.userId = UC.cardId")
    List<UserAndUserCard> getAllUserWithUserCard() throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllUser(List<User> userList) throws Exception;
}
