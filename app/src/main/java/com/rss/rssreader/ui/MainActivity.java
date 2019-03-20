package com.rss.rssreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.rss.rssreader.R;

public class MainActivity extends AppCompatActivity {

    private EditText etRssUrl;
    private ImageButton btRss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        btRss = findViewById(R.id.btRss);

        etRssUrl = findViewById(R.id.etRssUrl);
        etRssUrl.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                btRss.performClick();
            }
            return false;
        });
    }

    public void onRssBtClick(View view) {
        btRss.setClickable(false);
        Intent intent = new Intent(this, FeedActivity.class);
        if (!etRssUrl.getText().toString().trim().equals("")) {
            intent.putExtra("rssUrl", etRssUrl.getText().toString());
            Log.d("RSS", etRssUrl.getText().toString());
            btRss.setClickable(true);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Поле RSS-адреса не может быть пустым", Toast.LENGTH_LONG).show();
            btRss.setClickable(true);
        }
    }
}
