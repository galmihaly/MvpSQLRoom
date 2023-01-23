package hu.unideb.inf.mvpsqlroom.tasksmanager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

public class CustomHandlerThread extends HandlerThread {

    private CustomHandler mHandler;
    private WeakReference<PresenterThreadCallback> mUiThreadCallback;

    public CustomHandlerThread(String name){
        super(name, android.os.Process.THREAD_PRIORITY_BACKGROUND);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new CustomHandler(getLooper());
    }

    public void addMessage(int message){
        if(mHandler != null) {
            mHandler.sendEmptyMessage(message);
        }
    }

    public void postRunnable(Runnable runnable){
        if(mHandler != null) {
            mHandler.post(runnable);
        }
    }

    public void setPresenterCallback(PresenterThreadCallback callback){
        this.mUiThreadCallback = new WeakReference<>(callback);
    }

    private class CustomHandler extends Handler {
        public CustomHandler(Looper looper) {
            super(looper);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    try {
                        Thread.sleep(1000);
                        if(!Thread.interrupted() && mUiThreadCallback != null && mUiThreadCallback.get() != null){
                            Message message = Util.createMessage(Util.MESSAGE_ID, "Thread " + Thread.currentThread().getId() + " completed");
                            mUiThreadCallback.get().sendResultToPresenter(message);
                        }
                    } catch (InterruptedException e){
                        Log.e(Util.LOG_TAG,"HandlerThread interrupted");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
