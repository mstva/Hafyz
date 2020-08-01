package tech.mstava.hafyzapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import tech.mstava.hafyzapp.utils.GraphicOverlay;

public class RecognizeActivity extends AppCompatActivity {

    // request code for picking image from gallery
    private static final int PICK_IMAGE_REQUEST = 1;

    // store the buttons and views in variables
    private ImageButton mOpenCameraBtn, mOpenGalleryBtn;
    private Button mRecognizeBtn;
    private ImageView mRecognizeImageView;
    private GraphicOverlay graphicOverlay;

    // to store image picked from the gallery
    private Uri mImageUri;

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
                openGallery();
            }
        });

        mRecognizeBtn.setOnClickListener(new View.OnClickListener() {
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
            Picasso.with(this).load(mImageUri).into(mRecognizeImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}