package com.example.projectchicchic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity {

    Button buttonOpen;
    TextView textUri;
    ImageView imageView;
    TextView textVibrant, textVibrantDark, textVibrantLight;
    TextView textMuted, textMutedDark, textMutedLight;
    View viewVibrant, viewVibrantDark, viewVibrantLight;
    View viewMuted, viewMutedDark, viewMutedLight;

    private static final int RQS_OPEN_IMAGE = 1;

    Uri targetUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        textUri = (TextView) findViewById(R.id.texturi);
        imageView = (ImageView) findViewById(R.id.image);
        buttonOpen = (Button) findViewById(R.id.btnopen);
        buttonOpen.setOnClickListener(buttonOpenOnClickListener);

        textVibrant = (TextView)findViewById(R.id.textVibrant);
        textVibrantDark = (TextView)findViewById(R.id.textVibrantDark);
        textVibrantLight = (TextView)findViewById(R.id.textVibrantLight);
        textMuted = (TextView)findViewById(R.id.textMuted);
        textMutedDark = (TextView)findViewById(R.id.textMutedDark);
        textMutedLight = (TextView)findViewById(R.id.textMutedLight);

        viewVibrant = (View)findViewById(R.id.viewVibrant);
        viewVibrantDark = (View)findViewById(R.id.viewVibrantDark);
        viewVibrantLight = (View)findViewById(R.id.viewVibrantLight);
        viewMuted = (View)findViewById(R.id.viewMuted);
        viewMutedDark = (View)findViewById(R.id.viewMutedDark);
        viewMutedLight = (View)findViewById(R.id.viewMutedLight);
    }

    View.OnClickListener buttonOpenOnClickListener =
            new View.OnClickListener() {

                @TargetApi(Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {

//                    checkandroidversion();
                    Intent intent = new Intent();

                    if (Build.VERSION.SDK_INT >=
                            Build.VERSION_CODES.KITKAT) {
                        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                    } else {
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                    }

                    intent.addCategory(Intent.CATEGORY_OPENABLE);

                    // set MIME type for image
                    intent.setType("image/*");

                    startActivityForResult(intent, RQS_OPEN_IMAGE);
                }
            };
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && requestCode == Activity.RESULT_OK){
            Uri imageUri = CropImage.getPickImageResultUri(this,data);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getUri());

                    ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            Uri dataUri = data.getData();

            if (requestCode == RQS_OPEN_IMAGE) {
                targetUri = dataUri;
                textUri.setText(dataUri.toString());
                updatImage(dataUri);
            }
        }

    }

    private void updatImage(Uri uri) {
        if (uri != null){
            Bitmap bm;
            try {
                bm = BitmapFactory.decodeStream(
                        getContentResolver()
                                .openInputStream(uri));
                imageView.setImageBitmap(bm);

                extractProminentColors(bm);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void extractProminentColors(Bitmap bitmap) {
        int defaultColor = 0x000000;

        Palette p = Palette.from(bitmap).generate();

        int VibrantColor = p.getVibrantColor(defaultColor);
        textVibrant.setText("VibrantColor: " + String.format("#%X", VibrantColor));
        viewVibrant.setBackgroundColor(VibrantColor);

        int VibrantColorDark = p.getDarkVibrantColor(defaultColor);
        textVibrantDark.setText("VibrantColorDark: " + String.format("#%X", VibrantColorDark));
        viewVibrantDark.setBackgroundColor(VibrantColorDark);

        int VibrantColorLight = p.getLightVibrantColor(defaultColor);
        textVibrantLight.setText("VibrantColorLight: " + String.format("#%X", VibrantColorLight));
        viewVibrantLight.setBackgroundColor(VibrantColorLight);

        int MutedColor = p.getMutedColor(defaultColor);
        textMuted.setText("MutedColor: " + String.format("#%X", MutedColor));
        viewMuted.setBackgroundColor(MutedColor);

        int MutedColorDark = p.getDarkMutedColor(defaultColor);
        textMutedDark.setText("MutedColorDark: " + String.format("#%X", MutedColorDark));
        viewMutedDark.setBackgroundColor(MutedColorDark);

        int MutedColorLight = p.getLightMutedColor(defaultColor);
        textMutedLight.setText("MutedColorLight: " + String.format("#%X", MutedColorLight));
        viewMutedLight.setBackgroundColor(MutedColorLight);
    }

    public void checkandroidversion (){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            try {
                requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},555);
            }catch (Exception e){

            }
        }else {
            pickImage();
        }
    }

    public void pickImage(){
        CropImage.startPickImageActivity(this);
    }

    private void croprequest(Uri imageUri){
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();

        } else {
            checkandroidversion();
        }
    }

}