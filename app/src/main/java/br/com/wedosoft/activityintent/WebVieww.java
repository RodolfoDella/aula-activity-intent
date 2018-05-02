package br.com.wedosoft.activityintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebVieww extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_vieww);

        WebView webview = new WebView(this);
        webview.loadUrl("file:///android_asset/exemplo/index.html");
        setContentView(webview);
    }
}
