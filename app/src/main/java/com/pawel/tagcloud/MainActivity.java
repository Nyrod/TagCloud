package com.pawel.tagcloud;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        TagCloud tagCloud = new TagCloud(this);

//        TagCloud tagCloud = new TagCloud(this) {
//            @Override
//            public void onTagClick(String tagClicked) {
//                Toast.makeText(this.getContext(), tagClicked, Toast.LENGTH_LONG).show();
//            }
//      }


        relativeLayout.addView(tagCloud);
        tagCloud.addTag("basia", 61);
        tagCloud.addTag("ania", 32);
        tagCloud.addTag("sowa", 32);
        tagCloud.addTag("pies", 31);
        tagCloud.addTag("telefon", 27);
        tagCloud.addTag("dysk", 16);
        tagCloud.addTag("kot", 15);
        tagCloud.addTag("dom", 12);
        tagCloud.addTag("sadda", 81);

        relativeLayout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        relativeLayout.setVerticalGravity(Gravity.CENTER_VERTICAL);

        setContentView(relativeLayout);
    }
}
