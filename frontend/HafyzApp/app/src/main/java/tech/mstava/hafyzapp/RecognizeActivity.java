package tech.mstava.hafyzapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
                recognizeFace();
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

    private void recognizeFace() {
        // set a name for test image
        String testImageName = "unknown"
                + "-" + System.currentTimeMillis()
                + "." + getFileExtension(mImageUri);

        // set the local ip -- ubuntu local host ip address
        // TODO -- Change it in the future to real server
        String postUrl= "http://172.25.157.143:5000/test";

        // convert image to byte array
        // TODO -- Refactor this to a separate function
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] byteArray = stream.toByteArray();

        // collect data into request body
        RequestBody postBodyImage = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("test_image", testImageName, RequestBody.create(MediaType.parse("image/*jpg"), byteArray))
                .build();
    }
}