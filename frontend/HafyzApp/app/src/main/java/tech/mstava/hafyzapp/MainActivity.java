package tech.mstava.hafyzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mAddFaceBtn, mRecognizeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddFaceBtn = findViewById(R.id.add_face_button);
        mRecognizeBtn = findViewById(R.id.recognize_button);

        mAddFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFaceActivity.class);
                startActivity(intent);
            }
        });

        mRecognizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecognizeActivity.class);
                startActivity(intent);
            }
        });
    }
}