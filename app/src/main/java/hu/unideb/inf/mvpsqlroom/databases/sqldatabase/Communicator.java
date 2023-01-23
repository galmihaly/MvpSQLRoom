package hu.unideb.inf.mvpsqlroom.databases.sqldatabase;

import java.util.List;

public interface Communicator {

    public List<User> getAllUserData();
    public List<Card> getAllCardData();
    public List<UserCard> getAllUserCardsData();
}
