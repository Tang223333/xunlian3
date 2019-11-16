package com.lenovo.manufacture.thl.OkHttp;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp {
    private static OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3,TimeUnit.SECONDS)
            .writeTimeout(3,TimeUnit.SECONDS)
            .build();

    public JSONObject POST(Context context,String Url,String Json)throws Exception{
        RequestBody requestBody= FormBody.create(MediaType.parse("application/json,chatset=utf-8"),Json);

        String url="http://"
                +context.getSharedPreferences("IP",Context.MODE_PRIVATE).getString("ip","192.168.124.4")
                +":8088/transportservice/action/"+Url;

        Log.d("OkHttp", url);
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response=okHttpClient.newCall(request).execute();
        JSONObject jsonObject=new JSONObject(response.body().string());
        Log.d("OkHttp", "jsonObject:" + jsonObject);
        return  jsonObject;
    }
}
