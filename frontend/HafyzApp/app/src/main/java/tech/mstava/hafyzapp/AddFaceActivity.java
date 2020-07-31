package tech.mstava.hafyzapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AddFaceActivity extends AppCompatActivity {

    // for opening the gallery
    private static final int PICK_IMAGE_REQUEST = 1;

    // create variables for buttons and views
    private ImageButton mOpenCameraBtn, mOpenGalleryBtn;
    private Button mAddFaceBtn;
    private ImageView mAddFaceImageView;
    private EditText mPersonName;

    // to store image picked from the gallery
    private Uri mImageUri;

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
                openGallery();
            }
        });

        mAddFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST
                        && resultCode == RESULT_OK
                        && data != null
                        && data.getData() != null)
        {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mAddFaceImageView);
        }
    }
}