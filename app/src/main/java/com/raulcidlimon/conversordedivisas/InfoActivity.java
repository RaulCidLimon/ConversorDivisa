package com.raulcidlimon.conversordedivisas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InfoActivity extends AppCompatActivity {

    ImageButton ibFromInfo;
    Button btnGoToChangelog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ibFromInfo = findViewById(R.id.ibFromInfo);
        ibFromInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnGoToChangelog = findViewById(R.id.btnGoToChangelog);
        btnGoToChangelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, ChangelogActivity.class);
                startActivity(intent);
            }
        });
    }
}
