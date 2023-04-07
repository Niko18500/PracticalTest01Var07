package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {
    private Integer number00;
    private Integer number01;
    private Integer number10;
    private Integer number11;

    private TextView textView00;
    private TextView textView01;
    private TextView textView10;
    private TextView textView11;

    private Button sumButton;
    private Button productButton;

    private final ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.sum_button:
                    int sum = number00 + number01 + number10 + number11;
                    Toast.makeText(PracticalTest01Var07SecondaryActivity.this, "Sum: " + sum, Toast.LENGTH_LONG).show();
                    break;
                case R.id.product_button:
                    int product = number00 * number01 * number10 * number11;
                    Toast.makeText(PracticalTest01Var07SecondaryActivity.this, "Product: " + product, Toast.LENGTH_LONG).show();
                    break;
            }
//            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        textView00 = (TextView)findViewById(R.id.text_view_00);
        textView01 = (TextView)findViewById(R.id.text_view_01);
        textView10 = (TextView)findViewById(R.id.text_view_10);
        textView11 = (TextView)findViewById(R.id.text_view_11);

        sumButton = (Button)findViewById(R.id.sum_button);
        sumButton.setOnClickListener(buttonClickListener);
        productButton = (Button)findViewById(R.id.product_button);
        productButton.setOnClickListener(buttonClickListener);

        Intent intentFromParent = getIntent();
        if (intentFromParent != null) {
            String text00 = intentFromParent.getStringExtra(Constants.EDIT_TEXT_00);
            String text01 = intentFromParent.getStringExtra(Constants.EDIT_TEXT_01);
            String text10 = intentFromParent.getStringExtra(Constants.EDIT_TEXT_10);
            String text11 = intentFromParent.getStringExtra(Constants.EDIT_TEXT_11);

            textView00.setText(text00);
            textView01.setText(text01);
            textView10.setText(text10);
            textView11.setText(text11);

            number00 = Integer.parseInt(text00);
            number01 = Integer.parseInt(text01);
            number10 = Integer.parseInt(text10);
            number11 = Integer.parseInt(text11);
        }
    }
}