package hu.unideb.inf.mvpsqlroom.tasksmanager;

import android.os.Message;

import hu.unideb.inf.mvpsqlroom.models.ResultClass;

public interface PresenterThreadCallback {
    void sendResultToPresenter(Message message);
}
