package hu.unideb.inf.mvpsqlroom.databases.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.Card;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.UserCard;

public class CardAndUserCard {
    @Embedded
    public Card card;
    @Relation(
            parentColumn = "id",
            entityColumn = "userId"
    )
    public UserCard userCard;

    @Override
    public String toString() {
        return "CardAndUserCard{" +
                "card=" + card.toString() +
                ", userCard=" + userCard.toString() +
                '}';
    }
}
