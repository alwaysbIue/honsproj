package com.example.hosprojv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    String url ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Bundle extras = getIntent().getExtras();
        url = extras.getString("url");

        webView = findViewById(R.id.webview);

        // disable chaching
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setAppCacheEnabled(false);
        webView.clearHistory();
        webView.clearCache(true);
        webView.clearFormData();

        webView.loadUrl(url);
    }
}
