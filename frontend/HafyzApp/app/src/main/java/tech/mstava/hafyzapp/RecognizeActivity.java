package tech.mstava.hafyzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import tech.mstava.hafyzapp.utils.GraphicOverlay;

public class RecognizeActivity extends AppCompatActivity {

    // store the buttons and views in variables
    private ImageButton mOpenCameraBtn, mOpenGalleryBtn;
    private Button mRecognizeBtn;
    private ImageView mRecognizeImageView;
    private GraphicOverlay graphicOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize);

        // get the buttons and views by id
        mOpenCameraBtn = findViewById(R.id.open_camera_recognize);
        mOpenGalleryBtn = findViewById(R.id.open_gallery_recognize);
        mRecognizeBtn = findViewById(R.id.recognize_button);
        mRecognizeImageView = findViewById(R.id.recognize_image_view);
        graphicOverlay = findViewById(R.id.graphic_overlay);

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

        mRecognizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}