package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {
    private EditText editText00;
    private EditText editText01;
    private EditText editText10;
    private EditText editText11;

    private Button setButton;
    private Button startService;
    private Button stopService;

    private final ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Check if textboxes contain numbers
            String text00 = editText00.getText().toString();
            String text01 = editText01.getText().toString();
            String text10 = editText10.getText().toString();
            String text11 = editText11.getText().toString();
            if (text00.matches("[0-9]+") &&
                    text01.matches("[0-9]+") &&
                    text10.matches("[0-9]+") &&
                    text11.matches("[0-9]+")) {
                Intent intent = new Intent(PracticalTest01Var07MainActivity.this, PracticalTest01Var07SecondaryActivity.class);
                intent.putExtra(Constants.EDIT_TEXT_00, text00);
                intent.putExtra(Constants.EDIT_TEXT_01, text01);
                intent.putExtra(Constants.EDIT_TEXT_10, text10);
                intent.putExtra(Constants.EDIT_TEXT_11, text11);
                startActivity(intent);
            } else {
                Toast.makeText(PracticalTest01Var07MainActivity.this, "All fields must contain numbers!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private final ServiceButtonClickListener serviceButtonClickListener = new ServiceButtonClickListener();
    private class ServiceButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.start_service_button:
                    Intent intent = new Intent(PracticalTest01Var07MainActivity.this, PracticalTest01Var07Service.class);
                    startService(intent);
                    break;
                case R.id.stop_service_button:
                    stopService(new Intent(PracticalTest01Var07MainActivity.this, PracticalTest01Var07Service.class));
                    break;
            }
        }
    }

    private IntentFilter intentFilter = new IntentFilter();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text00 = intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA00);
            String text01 = intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA01);
            String text10 = intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA10);
            String text11 = intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA11);

            editText00.setText(text00);
            editText01.setText(text01);
            editText10.setText(text10);
            editText11.setText(text11);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        editText00 = (EditText)findViewById(R.id.edit_text_00);
        editText01 = (EditText)findViewById(R.id.edit_text_01);
        editText10 = (EditText)findViewById(R.id.edit_text_10);
        editText11 = (EditText)findViewById(R.id.edit_text_11);

        setButton = (Button)findViewById(R.id.set_button);
        setButton.setOnClickListener(buttonClickListener);

        startService = (Button)findViewById(R.id.start_service_button);
        startService.setOnClickListener(serviceButtonClickListener);
        stopService = (Button)findViewById(R.id.stop_service_button);
        stopService.setOnClickListener(serviceButtonClickListener);

        intentFilter.addAction(Constants.BROADCAST_RECEIVER_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.EDIT_TEXT_00, editText00.getText().toString());
        savedInstanceState.putString(Constants.EDIT_TEXT_01, editText01.getText().toString());
        savedInstanceState.putString(Constants.EDIT_TEXT_10, editText10.getText().toString());
        savedInstanceState.putString(Constants.EDIT_TEXT_11, editText11.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.EDIT_TEXT_00)) {
            editText00.setText(savedInstanceState.getString(Constants.EDIT_TEXT_00));
        }
        if (savedInstanceState.containsKey(Constants.EDIT_TEXT_01)) {
            editText01.setText(savedInstanceState.getString(Constants.EDIT_TEXT_01));
        }
        if (savedInstanceState.containsKey(Constants.EDIT_TEXT_10)) {
            editText10.setText(savedInstanceState.getString(Constants.EDIT_TEXT_10));
        }
        if (savedInstanceState.containsKey(Constants.EDIT_TEXT_11)) {
            editText11.setText(savedInstanceState.getString(Constants.EDIT_TEXT_11));
        }
    }
}