package tech.mstava.hafyzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddFaceActivity extends AppCompatActivity {

    // create variables for buttons and views
    private ImageButton mOpenCameraBtn, mOpenGalleryBtn;
    private Button mAddFaceBtn;
    private ImageView mAddFaceImageView;
    private EditText mPersonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_face);

        // get the buttons and views by id
        mOpenCameraBtn = findViewById(R.id.camera_add_face);
        mOpenGalleryBtn = findViewById(R.id.gallery_add_face);
        mAddFaceBtn = findViewById(R.id.add_face_button);
        mAddFaceImageView = findViewById(R.id.add_face_image_view);
        mPersonName = findViewById(R.id.enter_person_name);

        // set on click listener for buttons
        mOpenCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mOpenGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mAddFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}