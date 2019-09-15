package com.example.imageviewunderstanding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import static com.example.imageviewunderstanding.R.drawable.bit;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    ImageButton imageButton;
    ImageView imageView;
   final static int clickcode=100;
   Bitmap bitmap;
   Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton=findViewById(R.id.imageButton);
        imageView=findViewById(R.id.imageView);
        InputStream inputStream=getResources().openRawResource(R.drawable.bit);
        bitmap= BitmapFactory.decodeStream(inputStream);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"imageButton",Toast.LENGTH_SHORT).show();
                intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,clickcode);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Bundle bundle_pooja=data.getExtras();
            bitmap=(Bitmap)bundle_pooja.get("data");
            imageView.setImageBitmap(bitmap);
        }

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                getApplicationContext().setWallpaper(bitmap);
            }catch(IOException e)
                {
                    e.printStackTrace();
                }
        }
        });

    }

}

