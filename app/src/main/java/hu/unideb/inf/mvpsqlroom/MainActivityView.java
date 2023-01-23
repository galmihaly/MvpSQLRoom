package hu.unideb.inf.mvpsqlroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import hu.unideb.inf.mvpsqlroom.databases.room.Room;
import hu.unideb.inf.mvpsqlroom.interfaces.IMainActivityView;
import hu.unideb.inf.mvpsqlroom.models.ResultClass;
import hu.unideb.inf.mvpsqlroom.presenters.MainActivityPresenter;

public class MainActivityView extends AppCompatActivity implements IMainActivityView {

    public MainActivityPresenter mainActivityPresenter;
    public Button refreshButton;
    public Room room;
    public TextView textViewMy;
    public TextView textViewMy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshButton = findViewById(R.id.refreshButton);
        textViewMy = findViewById(R.id.textViewMy);
        textViewMy2 = findViewById(R.id.textViewMy2);
        mainActivityPresenter = new MainActivityPresenter(this);

        refreshButton.setOnClickListener(view -> mainActivityPresenter.getDatas());

        room = Room.getDatabase(getApplicationContext());

    }

    @Override
    public void refreshUiWithMessage(String message) {
        if(textViewMy != null) textViewMy.setText(message);
        Log.e("ffff", message);
    }

    @Override
    public void refreshUiWithObject(ResultClass<Object> result) {
        if(textViewMy2 != null){
            String s = String.valueOf(result.getResultModel());
            Log.e("dddd", result.getResultModel().toString());
            textViewMy2.setText(s);
        }
    }
}