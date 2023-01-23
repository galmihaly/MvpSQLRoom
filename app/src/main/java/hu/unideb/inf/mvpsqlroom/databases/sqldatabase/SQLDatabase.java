package hu.unideb.inf.mvpsqlroom.databases.sqldatabase;

import android.os.StrictMode;
import android.service.autofill.Dataset;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLDatabase implements Communicator{

    private Connection connection;
    private String query;
    private ResultSet rs;
    private User user;
    private Card card;
    private UserCard userCard;
    private int size;
    private Statement stmt;

    private final String _serverName = "server.logcontrol.hu";
    private final String _portNumber = "4241";
    private final String _databaseName = "GalMihalyTest";
    private final String _userId = "Galmihaly";
    private final String _password = "Gm2022!!!";

    private void getConnection() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            StringBuilder sb = new StringBuilder();

            String connectionURL = sb.append("jdbc:jtds:sqlserver://")
                    .append(_serverName)
                    .append(":").append(_portNumber)
                    .append("/").append(_databaseName)
                    .toString();

            Log.e("q", "connection előtt!!");

            connection = DriverManager.getConnection(connectionURL, _userId, _password);

            Log.e("q", "connection után!!");
        }
        catch (SQLException e) {
            Log.e("aaaaa", e.getMessage());
            Log.e("aaaaa", "sad");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            Log.e("hello", e.getMessage());
            Log.e("hello", "sad");
        }
    }


    @Override
    public List<User> getAllUserData() {
        getConnection();

        List<User>  userList = new ArrayList<>();
        Log.e("s", "nincs connection");
        if(connection != null) {
            try {
                query = "SELECT * FROM Users";
                Log.e("c", "van connection");
                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    user = new User();

                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setAccount(rs.getString(3));
                    user.setPassword(rs.getString(4));
                    user.setBarcode(rs.getString(5));
                    user.setAddress(rs.getString(6));
                    user.setActive(rs.getBoolean(7));

                    userList.add(user);
                    size++;
                }

                if(size == 0)  Log.i("s", "A lekérdezhető adatok száma 0!!!");

                connection.close();
            } catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!!!");
        }

        return userList;
    }

    @Override
    public List<Card> getAllCardData() {
        getConnection();

        List<Card>  cardList = new ArrayList<>();

        if(connection != null) {
            try {
                query = "SELECT * FROM Cards";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    card = new Card();

                    card.setId(rs.getInt(1));
                    card.setData(rs.getString(2));
                    card.setActive(rs.getBoolean(3));

                    cardList.add(card);
                    size++;
                }

                if(size == 0)  Log.i("s", "A lekérdezhető adatok száma 0!!!");

                connection.close();
            } catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!!!");
        }

        return cardList;
    }

    @Override
    public List<UserCard> getAllUserCardsData() {
        getConnection();

        List<UserCard> userCardsList = new ArrayList<>();

        if(connection != null) {
            try {
                query = "SELECT * FROM UserCards";

                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                size = 0;

                while (rs.next()) {
                    userCard = new UserCard();

                    userCard.setUserId(rs.getInt(1));
                    userCard.setCardId(rs.getInt(2));

                    userCardsList.add(userCard);
                    size++;
                }

                if(size == 0)  Log.i("s", "A lekérdezhető adatok száma 0!!!");

                connection.close();
            } catch (SQLException e) {
                Log.e("s", "Sikertelen olvasás az adatbázisból!!!");
                e.printStackTrace();
            }
        }
        else{
            Log.i("s", "Nem alakult ki kapcsolat az adatbázis és az alkalmazás között!!!");
        }

        return userCardsList;
    }
}
