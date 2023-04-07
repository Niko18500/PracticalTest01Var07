package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Random;

public class ProcessingThread extends Thread {
    private Context context;
    private boolean isRunning;
    private Random random;

    public ProcessingThread(Context context) {
        this.context = context;
        this.isRunning = true;
        random = new Random();
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());

        while (isRunning) {
            waitTenSeconds();
            sendMessage();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void waitTenSeconds() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_RECEIVER_ACTION);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA00, String.valueOf(random.nextInt(20)));
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA01, String.valueOf(random.nextInt(20)));
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA10, String.valueOf(random.nextInt(20)));
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA11, String.valueOf(random.nextInt(20)));

        context.sendBroadcast(intent);
    }

    public void stopThread() {
        isRunning = false;
    }
}
