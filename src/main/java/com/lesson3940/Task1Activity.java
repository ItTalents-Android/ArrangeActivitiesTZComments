package com.lesson3940;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Task1Activity extends AppCompatActivity {

    Button btn;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

        root = findViewById(R.id.parentlayout);

        btn = (Button) findViewById(R.id.nexttask);


        View toDel = findViewById(R.id.cl);
        if (toDel != null){
            toDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ViewGroup) v.getParent()).removeView(v);
                }
            });
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Task1Activity.this, Task2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        super.onPause();
    }

    @Override
    public void onResume() {
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        super.onResume();
    }

    public void delete(View v) {
    }
}
