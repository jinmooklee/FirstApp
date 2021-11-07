package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewsViewActivity extends AppCompatActivity {
    TextView textViewTitle;
    TextView textViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        // 필드 id 값 읽어오기
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewContent = findViewById(R.id.textViewContent);

        // Intent 로 뉴스 항목들 읽어들이기
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 뉴스 항목들 해석하기?
        NewsData news = (NewsData) bundle.get("news");

        String title = news.getTitle();
        String content = news.getDescription();

        // 표시 항목들에 값 넣기
        textViewTitle.setText(title);
        textViewContent.setText(content);

    }
}