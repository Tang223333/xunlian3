package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.hxf.Utils.MainApplication;


/**
 * @author Amoly
 * @date 2019/10/24.
 * GitHub：
 * email：
 * description：
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
        initWebView();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {

        TWebView webView = new TWebView(this, null);
        ViewGroup viewParent = findViewById(R.id.webView1);
        viewParent.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        webView.loadUrl("file:///android_asset/index.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                /* mWebView.showLog("test Log"); */
            }
        });
        //TODO 启用Java脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //TODO 设置Java脚本可以自动打开窗口
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setBackgroundColor(0);
        //TODO 请求焦点
        webView.requestFocus();
        //TODO 添加Javascript接口
        /**
         * TODO 只有声明了@JavascriptInterace注解的方法才能被Web页面调用
         *
         * 为了与Web页面实现动态交互，Android应用程序允许WebView通过WebView.addJavascriptInterface接口向Web页面注入Java对象，
         * 页面Javascript脚本可直接引用该对象并调用该对象的方法。
         * 这类应用程序一般都会有类似如下的代码：
         *      webView.addJavascriptInterface(javaObj, "jsObj");
         * 此段代码将javaObj对象暴露给js脚本，可以通过jsObj对象对其进行引用，调用javaObj的方法。
         * 结合Java的反射机制可以通过js脚本执行任意Java代码。
         *
         */
        //nativeMethod是js调用原生时的别名，非固定写法
        webView.addJavascriptInterface(new JavaScriptInterface(this), "nativeMethod");
        WebSettings webSetting = webView.getSettings();
        //TODO 设置允许文件访问
        webSetting.setAllowFileAccess(true);
        //TODO 设置布局算法
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        //TODO 设置支持缩放
        webSetting.setSupportZoom(true);
        //TODO 设置内建在缩放控制
        webSetting.setBuiltInZoomControls(true);
        //TODO 设置使用宽的视图端口
        webSetting.setUseWideViewPort(true);
        //TODO 支持多个窗口
        webSetting.setSupportMultipleWindows(false);
        //TODO 设置App缓存启用
        webSetting.setAppCacheEnabled(true);
        //TODO 启用设置Dom存储
        webSetting.setDomStorageEnabled(true);
        //TODO 设置默认的文本编码名称
        webSetting.setDefaultTextEncodingName("utf-8");
        //TODO 设置应用程序缓存的最大大小
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        //TODO 得到资源.得到显示指标
        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        //TODO 变焦密度
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        //TODO 设置默认缩放
        webSetting.setDefaultZoom(zoomDensity);
    }


    public class JavaScriptInterface {
        Activity mActivity;

        JavaScriptInterface(Activity mActivity) {
            this.mActivity = mActivity;
        }

        /**
         * 与js交互时用到的方法，在js里直接调用的
         */
        @JavascriptInterface
        public void startActivity(int id) {
            switch (id) {
                case 1:
                    Intent intent1 = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
//                    intent1.setClass(mActivity, TestActivity.class);
                    intent1.setClass(mActivity, Main1Activity.class);
                    mActivity.startActivity(intent1);
                    break;
                case 2:
                    Intent intent2 = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
                    intent2.setClass(mActivity, Main2Activity.class);
                    mActivity.startActivity(intent2);
                    break;
                case 3:
                    Intent intent3 = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
                    intent3.setClass(mActivity, Main3Activity.class);
                    mActivity.startActivity(intent3);
                    break;
                case 4:
                    Intent intent4 = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
                    intent4.setClass(mActivity, Main4Activity.class);
                    mActivity.startActivity(intent4);
                    break;
                case 5:
                    Intent intent5 = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
                    intent5.setClass(mActivity, Main5Activity.class);
                    mActivity.startActivity(intent5);
                    break;
                case 6:
                    Intent intent6 = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
                    intent6.setClass(mActivity, Main6Activity.class);
                    mActivity.startActivity(intent6);
                    break;
                case 7:
                    Intent intent7 = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
                    intent7.setClass(mActivity, Main7Activity.class);
                    mActivity.startActivity(intent7);
                    break;
                case 8:
                    Intent intent8 = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
                    intent8.setClass(mActivity, Main8Activity.class);
                    mActivity.startActivity(intent8);
                    break;

            }


//            Intent intent = new Intent();
////            intent.putExtra("fromWhich", "webViewUrl");
////            intent.putExtra("replyID", "replyID");
//            intent.setClass(mActivity, TestActivity.class);
//            mActivity.startActivity(intent);
        }

    }
    private void initView() {
//        do {
//            //TODO 获取是否为第一次启动APP（具体操作请查看源码）
//            MainApplication.getIsOneStartApp();
//        }while (MainApplication.getIsOneStartApp());
//
    }

}
