package edu.quinnipiac.ser210.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String userName;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText userNameBox = (EditText) findViewById(R.id.userNameBox);
        startButton = (Button) findViewById(R.id.startButton);
        userNameBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userName = s.toString();
                startButton.setEnabled(true);
            }
        });
    }

    public void onStartButtonClick(View view) {
        Intent startGameIntent = new Intent(this, GameGridActivity.class);
        startGameIntent.putExtra("Name",userName);
        startActivity(startGameIntent);
    }


}