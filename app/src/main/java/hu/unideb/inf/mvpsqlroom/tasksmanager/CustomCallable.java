package hu.unideb.inf.mvpsqlroom.tasksmanager;

import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.CommunicatorTypeEnums;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.Repository;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.User;
import hu.unideb.inf.mvpsqlroom.models.ResultClass;

public class CustomCallable implements Callable {

    private WeakReference<CustomThreadPoolManager> customThreadPoolManagerWeakReference;

    public void setCustomThreadPoolManager(CustomThreadPoolManager customThreadPoolManager) {
        this.customThreadPoolManagerWeakReference = new WeakReference<>(customThreadPoolManager);
    }

    @Override
    public Object call() {

        try {
            if (Thread.interrupted()) throw new InterruptedException();

            Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);
            List<User> userList = repository.Communicator.getAllUserData();
            Log.e("listaméret:", String.valueOf(userList.size()));
            for (int i = 0; i < userList.size(); i++) {
                Log.e(String.valueOf(i), String.valueOf(userList.get(i)));
            }

            Message message = Util.createMessage(Util.MESSAGE_ID, "Thread " +
                    Thread.currentThread().getId() + " " + Thread.currentThread().getName() + " completed");

            message.obj = "sas";

            if(customThreadPoolManagerWeakReference != null && customThreadPoolManagerWeakReference.get() != null) {
                customThreadPoolManagerWeakReference.get().sendResultToPresenter(message);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
