package com.raulcidlimon.conversordedivisas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChangelogActivity extends AppCompatActivity {

    ImageButton ibFromChangelog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelog);

        ibFromChangelog = findViewById(R.id.ibFromChangelog);
        ibFromChangelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}