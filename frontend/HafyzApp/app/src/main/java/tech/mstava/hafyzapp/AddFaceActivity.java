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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
                addFace();
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

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void addFace() {

        // get the person name entered by the user
        String personName = mPersonName.getText().toString().trim();

        // set the image name
        String trainImageName =
                personName.replaceAll(" ", "-").toLowerCase()
                + "-" + System.currentTimeMillis()
                + "." + getFileExtension(mImageUri);

        // the local server url
        // TODO -- Change it in the future to real server
        String postUrl= "http://192.168.1.2:5000/train";

        // convert the image to byte array format
        // TODO -- Refactor this to a separate function
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] byteArray = stream.toByteArray();

        // collect the data above in the request body to sent it to server
        RequestBody postBodyImage = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("person_name", personName)
                .addFormDataPart("train_image", trainImageName, RequestBody.create(MediaType.parse("image/*jpg"), byteArray))
                .build();

        // after getting the data, remove the person name text field
        mPersonName.setText("");
    }
}