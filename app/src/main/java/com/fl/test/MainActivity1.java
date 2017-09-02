package com.fl.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();
//        try {
//            Response responses = okHttpClient.newCall(request).execute();
//            if (!responses.isSuccessful())
//                throw new IOException("Unexception code"+responses);
//            Headers headers = responses.headers();
//            for (int i = 0; i < headers.size(); i++) {
//                System.out.println(headers.name(i) + ": " + headers.value(i));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            run3();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private Handler handler = new Handler(Looper.getMainLooper());
    private final OkHttpClient client = new OkHttpClient();
//    private static final MediaType MEDIA_TYPE_MARKDOWN
//            = MediaType.parse("text/x-markdown; charset=utf-8");

//    public void run() throws Exception {
//        Request request = new Request.Builder()
//                .url("http://publicobject.com/helloworld.txt")
//                .build();
//
//
//        Call call=client.newCall(request);
//
//        //异步执行的方法
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                byte[] bytes = response.body().bytes();
//                InputStream inputStream = response.body().byteStream();
//                Reader reader = response.body().charStream();
//                String string = response.body().string();
//                response.isSuccessful();
//
//                //更改UI需要使用Handler/runOnUiThread(Runnable runnable);
//            }
//        });

//        client.newCall(new Request.Builder().post(new MultipartBody.))
//        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//        Headers responseHeaders = response.headers();
//        for (int i = 0; i < responseHeaders.size(); i++) {
//            TextView tv = (TextView) findViewById(R.id.tv);
//            tv.append(responseHeaders.name(i)+"dsfsdfsd\n/n/n/n/n/"+responseHeaders.value(i)+"\n"+response.body().string());
//            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));

    //        System.out.println(response.body().string());
//    }

    public void run3() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        });

    }
    private static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    public void runPost() throws Exception {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        FormBody body = new FormBody.Builder().build();

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
//                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                System.out.println(response.body().string());
            }
        });

    }

    private Gson gson = new Gson();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();
        client.readTimeoutMillis();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Gist gist = gson.fromJson(response.body().charStream(), Gist.class);
                for (
                        Map.Entry<String, GistFile> entry
                        : gist.files.entrySet())

                {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue().content);
                }


                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.i("rsponse", headers.name(i) + "::::::::::::" + headers.value(i));
                }

            }
        });
    }


    class Gist {
        Map<String, GistFile> files;
    }

    class GistFile {
        String content;
    }
}



