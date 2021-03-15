package uz.eldor.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BlogActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        webView = findViewById(R.id.web_view2);

        Intent intent = getIntent();
        String blog = intent.getStringExtra("blog");

        webView.loadUrl(blog);
        webView.setWebViewClient(new WebViewClient());

    }
}