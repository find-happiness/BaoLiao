package com.happiness.baoliao;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  WebView webview;
  WebView webview2;
  Subscription subscription;

  Subscription subscription2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.activity_main);

    webview = (WebView) this.findViewById(R.id.webview);
    webview2 = (WebView) this.findViewById(R.id.webview2);
    initWebView();

    //webview.loadUrl("http://baoliao.cq.qq.com/pc/detail.html?id=412775");
    subscription = Observable.interval(1000, 10000, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Long>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(Long aLong) {
            //http://cqwz.cqnews.net/redirect-8a3a5874-d766-44d7-9662-4a9989c0556e.html
            webview.loadUrl("http://baoliao.cq.qq.com/pc/detail.html?id=412850");
          }
        });

    subscription2 = Observable.interval(1000, 10300, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Long>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(Long aLong) {
            //http://cqwz.cqnews.net/redirect-8a3a5874-d766-44d7-9662-4a9989c0556e.html
            webview2.loadUrl("http://cqwz.cqnews.net/redirect-8a3a5874-d766-44d7-9662-4a9989c0556e.html");
          }
        });
  }

  private void initWebView() {
    webview.setVisibility(WebView.VISIBLE);
    webview.getSettings().setSupportZoom(false);
    webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    webview.getSettings().setBuiltInZoomControls(false);
    webview.getSettings().setJavaScriptEnabled(true);
    webview.clearCache(true);
    webview.setWebViewClient(new WebViewClient() {
      @Override public void onPageFinished(WebView view, String url) {

      }
    });

    webview2.setVisibility(WebView.VISIBLE);
    webview2.getSettings().setSupportZoom(false);
    webview2.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    webview2.getSettings().setBuiltInZoomControls(false);
    webview2.getSettings().setJavaScriptEnabled(true);
    webview2.clearCache(true);
    webview2.setWebViewClient(new WebViewClient() {
      @Override public void onPageFinished(WebView view, String url) {

      }
    });
  }

  @Override public void onStart() {
    super.onStart();
  }

  @Override public void onStop() {
    super.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (subscription != null && subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
    if (subscription2 != null && subscription2.isUnsubscribed()) {
      subscription2.unsubscribe();
    }
  }
}
