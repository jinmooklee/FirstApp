package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewsViewActivity extends AppCompatActivity {
    TextView textViewTitle, textViewContent;
    private NewsData news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        // 필드 id 값 읽어오기
        setFieldId();
        // Intent 로 뉴스 항목들 읽어들이기
        getNewsDetail();
        // 표시 항목들에 값 넣기
        setNews();
    }
    protected void setFieldId() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewContent = findViewById(R.id.textViewContent);
    }

    protected void getNewsDetail() {
        Intent intent = getIntent();

        if (intent != null) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object obj = bundle.get("news");
                if (obj instanceof NewsData) this.news = (NewsData) obj;
            }
        }
    }

    protected void setNews() {
        if (this.news != null) {
            String title = news.getTitle();
            String content = news.getDescription();

            textViewTitle.setText(title);
            textViewContent.setText(content);
        }
    }
}