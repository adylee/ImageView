package com.example.adly.imageview;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int[]images=new int[]{
            R.drawable.ic_launcher,
            R.drawable.add,
            R.drawable.bdd,
            R.drawable.aa,
            R.drawable.ab,
            R.drawable.ac
    };
    int currentImage=2;
    private int alpha = 255;

   @Override
    public void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.content_main);
       final Button plus=(Button)findViewById(R.id.plus);
       final Button minus=(Button)findViewById(R.id.minus);
       final ImageView image1=(ImageView)findViewById(R.id.add);
       final ImageView image2=(ImageView)findViewById(R.id.bdd);
       final Button next=(Button)findViewById(R.id.next);
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               image1.setImageResource(
                       images[++currentImage % images.length ]
               );
           }
       });
       View.OnClickListener listner = new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(v==plus)
               {
                   alpha+=20;
               }
               if(v==minus)
               {
                   alpha-=20;
               }
               if(alpha>=255)
               {
                   alpha=255;
               }
               if(alpha<=0)
               {
                   alpha=0;
               }
               image1.setAlpha(alpha);
           }
       };
       plus.setOnClickListener(listner);
       minus.setOnClickListener(listner);
       image1.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               BitmapDrawable bitmapDrawable=(BitmapDrawable)image1.getDrawable();
               Bitmap bitmap=bitmapDrawable.getBitmap();
               double scale=bitmap.getWidth()/320.0;
               int x=(int)(event.getX()*scale);
               int y=(int)(event.getY()*scale);
               if(x+120>bitmap.getWidth())
               {
                   x=bitmap.getWidth()-120;
               }
               if(y+120>bitmap.getHeight())
               {
                   y=bitmap.getHeight()-120;
               }
               image2.setImageBitmap(Bitmap.createBitmap(bitmap,x,y,120,120));
               image2.setAlpha(alpha);
               return false;


           }
       });

   }
}
