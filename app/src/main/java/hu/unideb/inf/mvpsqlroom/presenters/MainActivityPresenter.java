package hu.unideb.inf.mvpsqlroom.presenters;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import hu.unideb.inf.mvpsqlroom.databases.room.Room;
import hu.unideb.inf.mvpsqlroom.databases.room.UserAndUserCard;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.Card;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.CommunicatorTypeEnums;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.Repository;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.User;
import hu.unideb.inf.mvpsqlroom.databases.sqldatabase.UserCard;
import hu.unideb.inf.mvpsqlroom.interfaces.IMainActivityPresenter;
import hu.unideb.inf.mvpsqlroom.interfaces.IMainActivityView;
import hu.unideb.inf.mvpsqlroom.tasksmanager.CustomCallable;
import hu.unideb.inf.mvpsqlroom.tasksmanager.CustomHandlerThread;
import hu.unideb.inf.mvpsqlroom.tasksmanager.CustomThreadPoolManager;
import hu.unideb.inf.mvpsqlroom.tasksmanager.PresenterThreadCallback;
import hu.unideb.inf.mvpsqlroom.tasksmanager.Util;

public class MainActivityPresenter implements IMainActivityPresenter, PresenterThreadCallback {

    private IMainActivityView iMainActivityView;

    private ExecutorService executor = null;
    private FutureTask<String> futureTask1 = null;
    private FutureTask<String> futureTask2 = null;
    private boolean isNotDone = true;
    private Room room;

    private CustomHandlerThread mHandlerThread;
    private CustomThreadPoolManager mCustomThreadPoolManager;
    private UiHandler mUiHandler;

    public MainActivityPresenter(IMainActivityView iMainActivityView) {
        this.iMainActivityView = iMainActivityView;
    }

    @Override
    public void getDatas() {
        initTaskManager();
        method2();
        //work1(this::initTaskManager, this::method2);
    }

    public void initTaskManager(){
        mUiHandler = new UiHandler(Looper.myLooper(), iMainActivityView);

        mHandlerThread = new CustomHandlerThread("HandlerThread");
        mHandlerThread.setPresenterCallback(this);
        mHandlerThread.start();

        mCustomThreadPoolManager = CustomThreadPoolManager.getsInstance();
        mCustomThreadPoolManager.setPresenterCallback(this);
    }

    /*public void work1(Runnable runnable1, Runnable runnable2){
        if(iMainActivityView == null) return;

        // ThreadPoolExecutor-ra való esetleges lecserélés
        executor = Executors.newSingleThreadExecutor();
        
        if(runnable1 != null){

            futureTask1 = new FutureTask<>(runnable1, "FutureTask1 is done!!!");

            executor.submit(futureTask1);
            isNotDone = true;

            while(isNotDone){
                if (futureTask1.isDone()) {
                    //Log.e("l:", "FutureTask1 is complete!!!");

                    isNotDone = false;
                }
                else if (futureTask1.isCancelled()) Log.e("l:", "FutureTask1 is cancelled!!!");
                //else Log.e("k:", "FutureTask1 is not complete!!!");
            }
        }

        if(runnable2 != null){

            futureTask2 = new FutureTask<>(runnable2, "FutureTask2 is done!!!");
            executor.submit(futureTask2);
            isNotDone = true;

            while(isNotDone){
                if (futureTask2.isDone()) {
                    //Log.e("l:", "FutureTask2 is complete!!!");

                    isNotDone = false;
                }
                else if (futureTask2.isCancelled()) Log.e("l:", "FutureTask2 is cancelled!!!");
                //else Log.e("k:", "FutureTask2 is not complete!!!");
            }
        }

    }*/

    protected void method1(){
        Repository repository = new Repository(CommunicatorTypeEnums.MsSQLServer);
        List<User> userList = repository.Communicator.getAllUserData();

        for (int i = 0; i < userList.size(); i++) {
            Log.e(String.valueOf(i), String.valueOf(userList.get(i)));
        }

        Log.e("", "-----------------------------------------------------------------------");

        List<Card> cardList = repository.Communicator.getAllCardData();

        for (int i = 0; i < cardList.size(); i++) {
            Log.e(String.valueOf(i), String.valueOf(cardList.get(i)));
        }

        Log.e("", "-----------------------------------------------------------------------");

        List<UserCard> userCardList = repository.Communicator.getAllUserCardsData();

        for (int i = 0; i < userCardList.size(); i++) {
            Log.e(String.valueOf(i), String.valueOf(userCardList.get(i)));
        }

        room = Room.getInstance();

        if(room != null){
            try {
                room.usersDAO().insertAllUser(userList);
                room.cardsDAO().insertAllCard(cardList);
                room.userCardDAO().truncateUserCardTable();
                room.userCardDAO().insertAllUserCard(userCardList);

                List<User> list1 = room.usersDAO().getAllUser();
                List<Card> list2 = room.cardsDAO().getAllCard();
                List<UserAndUserCard> list3 = room.usersDAO().getAllUserWithUserCard();
                List<Card> list4 = room.cardsDAO().getAllCardWithUserCard();
                List<UserCard> list5 = room.userCardDAO().getAllUserCards();

                for (int i = 0; i < list1.size(); i++) {
                    Log.i( String.valueOf(i), String.valueOf(list1.get(i).toString()));
                }
                Log.i("", "-----------------------------------------------------------------");
                for (int i = 0; i < list2.size(); i++) {
                    Log.i( String.valueOf(i), String.valueOf(list2.get(i).toString()));
                }
                Log.i("", "-----------------------------------------------------------------");
                for (int i = 0; i < list5.size(); i++) {
                    Log.i( String.valueOf(i), String.valueOf(list5.get(i).toString()));
                }
                Log.i("", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                Log.i("", String.valueOf(list3.size()));
                for (int i = 0; i < list3.size(); i++) {
                    Log.i( String.valueOf(i), String.valueOf(list3.get(i).toString()));
                }
                Log.i("", "-----------------------------------------------------------------");
                Log.i("", String.valueOf(list4.size()));
                for (int i = 0; i < list4.size(); i++) {
                    if(list4.get(i) != null){
                        Log.i( String.valueOf(i), String.valueOf(list4.get(i).toString()));
                    }
                    else
                        Log.i( String.valueOf(i), String.valueOf(list4.get(i)));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void method2(){
        CustomCallable callable = new CustomCallable();
        callable.setCustomThreadPoolManager(mCustomThreadPoolManager);
        mCustomThreadPoolManager.addCallableMethod(callable);
    }


    @Override
    public void sendResultToPresenter(Message message) {
        if(mUiHandler == null) return;
        mUiHandler.sendMessage(message);
    }


    private static class UiHandler extends Handler {

        private WeakReference<IMainActivityView> iMainActivityViewWeakReference;

        public UiHandler(Looper looper, IMainActivityView iMainActivityView) {
            super(looper);
            this.iMainActivityViewWeakReference = new WeakReference<>(iMainActivityView);
        }

        // This method will run on UI thread
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Util.MESSAGE_ID:
                    Bundle bundle = msg.getData();
                    String messsageText = bundle.getString(Util.MESSAGE_BODY, Util.EMPTY_MESSAGE);
                    Log.e("", messsageText);
                    String bundle1 = (String) msg.obj;
                    iMainActivityViewWeakReference.get().refreshUiWithObject(msg.obj);
                default:
                    break;
            }
        }
    }
}
