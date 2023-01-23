package hu.unideb.inf.mvpsqlroom.databases.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.Card;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.User;

@Dao
public interface CardsDAO {

    @Query("SELECT * FROM cards_table")
    List<Card> getAllCard() throws Exception;

    @Transaction
    @Query("SELECT * FROM cards_table C " +
            "INNER JOIN UserCard UC ON UC.cardId = C.id " +
            "WHERE UC.cardId = C.id")
    List<Card> getAllCardWithUserCard() throws Exception;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCard(List<Card> cardList) throws Exception;
}
