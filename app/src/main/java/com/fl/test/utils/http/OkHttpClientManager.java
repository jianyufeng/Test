package com.fl.test.utils.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.fl.test.utils.imageutils.ImageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.internal.$Gson$Types;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/3/28.
 */
public class OkHttpClientManager {
    private static OkHttpClientManager mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;
    private Gson mGson;
    private static final String TAG = "OkHttpClientManager";
    private static final String SESSION_KEY = "Set-Cookie";//?
    private static final String mSessionKey = "JSESSIONID";//?
    private Map<String, String> mSessions = new HashMap<String, String>();//?

    private OkHttpClientManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //在OkHttpClient创建时，传入这个CookieJar的实现，就能完成对Cookie的自动管理了
        builder.cookieJar(new CookieJar() {
            private final HashMap<HttpUrl, List<Cookie>> cookieStore = new
                    HashMap<HttpUrl, List<Cookie>>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url, cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url);
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });

        //cookie enabled   差缓存？？？？？？？？？？
        mOkHttpClient = builder.build();

//        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    //对外提供的单例模式来创建OkHttpClientManager对象
    public static OkHttpClientManager getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 同步的Get请求
     *
     * @param url
     * @return Response
     * @throws IOException
     */
    private Response _getAsyn(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response execute = call.execute();
//        Request.Builder builder = request.newBuilder();
//        List<Cookie> cookies = mOkHttpClient.cookieJar().loadForRequest(request.url());
//        if (!cookies.isEmpty()){
//            StringBuilder stringBuilder = new StringBuilder();
//            if (mOkHttpClient.cookieJar()== CookieJar.NO_COOKIES);
//            List<Cookie> cookies1 = Cookie.parseAll(request.url(), );
//            mOkHttpClient.cookieJar().saveFromResponse(request.url(),cookies1);
//            for (int i = 0; i < cookies.size(); i++) {
//                if (i>0){
//                    stringBuilder.append(";");
//                }
//                Cookie cookie = cookies.get(i);
//                stringBuilder.append(cookie.name())
//                        .append("=")
//                        .append(cookie.value());
//
//            }
//            builder.header("Cookie",stringBuilder.toString());
//        }

        return execute;
    }


    /**
     * 同步的Get请求
     *
     * @param url
     * @return String 字符串
     * @throws IOException
     */
    private String _getAsString(String url) throws IOException {
        Response response = _getAsyn(url);
        return response.body().string();
    }


    /**
     * 异步的get请求
     *
     * @param url
     * @param callback
     */
    private void _getAsyn(String url, ResultCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request);
    }

    private void deliveryResult(final ResultCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(call.request(), e, callback);//request???
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //少tryCatch？？？？？？？？？？？？？？？？？
                try {
                    final String string = response.body().string();  //final????
                    if (callback.mType == String.class) {
                        sendSuccessResultCallback(string, callback);
                    } else {
                        Object o = mGson.fromJson(string, callback.mType);
                        sendSuccessResultCallback(o, callback);
                    }
                } catch (JsonParseException e)//Json解析的错误
                {
                    sendFailedStringCallback(response.request(), e, callback);
                }
            }
        });
    }

    //    private void deliveryResult();
//    private void deliveryResult(final ResultCallback callback, final Request request) {
//
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                sendFailedStringCallback(request, e, callback);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    final String string = response.body().string();
//                    if (callback.mType == String.class) {
//                        sendSuccessResultCallback(string, callback);
//                    } else {
//                        Object o = mGson.fromJson(string, callback.mType);
//                        sendSuccessResultCallback(o, callback);
//                    }
//
//
//                } catch (IOException e) {
//                    sendFailedStringCallback(response.request(), e, callback);
//                } catch (com.google.gson.JsonParseException e)//Json解析的错误
//                {
//                    sendFailedStringCallback(response.request(), e, callback);
//                }
//            }
//        });
//    }

    /**
     * 同步的Post请求
     *
     * @param url
     * @param params
     * @return response
     * @throws IOException
     */
    private Response _post(String url, Param... params) throws IOException {
        Request request = buildPostRequest(url, params);
        Response response = mOkHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 同步的Post请求
     *
     * @param url
     * @param params post的参数
     * @return 字符串
     */
    private String _postAsString(String url, Param... params) throws IOException {
        Response response = _post(url, params);
        return response.body().string();
    }

    /**
     * 异步的post请求
     *
     * @param url
     * @param callback
     * @param params
     */
    private void _postAsyn(String url, final ResultCallback callback, Param... params) { //final ???
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    /**
     * 异步的post请求
     *
     * @param url
     * @param callback
     * @param params
     */
    private void _postAsyn(String url, ResultCallback callback, Map<String, String> params) {
        Param[] paramsArr = map2Params(params);
        _postAsyn(url, callback, paramsArr);  //??????Request request = buildPostRequest(url, paramsArr);deliveryResult(callback, request);

    }

    /**
     * 同步基于post的文件上传
     *
     * @param url
     * @param files
     * @param fileKeys
     * @param params
     * @return
     */
    private Response _post(String url, File[] files, String[] fileKeys, Param... params) throws IOException {//????
        Request request = buildMultipartFormRequest(url, files, fileKeys, params);
        return mOkHttpClient.newCall(request).execute();
    }

    private Response _post(String url, File file, String fileKey) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, null);
        return mOkHttpClient.newCall(request).execute();
    }

    private Response _post(String url, File file, String fileKey, Param... params) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, params);
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 异步基于post的文件上传
     *
     * @param url
     * @param callback
     * @param files
     * @param fileKeys
     * @throws IOException
     */
    private void _postAsyn(String url, ResultCallback callback, File[] files, String[] fileKeys, Param... params) throws IOException {
        Request request = buildMultipartFormRequest(url, files, fileKeys, params);
        deliveryResult(callback, request);
    }

    /**
     * 异步基于post的文件上传，单文件不带参数上传
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     * @throws IOException
     */
    private void _postAsyn(String url, ResultCallback callback, File file, String fileKey) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, null);
        deliveryResult(callback, request);
    }

    /**
     * 异步基于post的文件上传，单文件且携带其他form参数上传
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     * @param params
     * @throws IOException
     */
    private void _postAsyn(String url, ResultCallback callback, File file, String fileKey, Param... params) throws IOException {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, params);
        deliveryResult(callback, request);
    }

    /**
     * 异步下载文件
     *
     * @param url
     * @param destFileDir 本地文件存储的文件夹
     * @param callback
     */
    private void _downloadAsyn(final String url, final String destFileDir, final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                byte[] buf = new byte[1024];
                int len = 0;
                FileOutputStream fos = null;
                is = response.body().byteStream();
                File file = new File(destFileDir, getFileName(url));
                try {
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    // 如果下载文件成功, 返回下载后文件的绝对路径  第一个参数为文件的绝对路径
                    sendSuccessResultCallback(file.getAbsolutePath(), callback);
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } finally {
                    if (is != null) try {
                        is.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (fos != null) try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

    /**
     * 获取文件名
     *
     * @param path
     * @return
     */
    private String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    private void _displayImage(final ImageView view, final String url, final int errorImageID) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                setErrorResId(view, errorImageID);
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                try {
                    is = response.body().byteStream();
                    ImageUtils.ImageSize actualImageSize = ImageUtils.getImageSize(is);
                    ImageUtils.ImageSize imageViewSize = ImageUtils.getImageViewSize(view);
                    int inSampleSize = ImageUtils.calculateInSampleSize(actualImageSize, imageViewSize);
                    try {
                        is.reset();

                    } catch (IOException e) {
                        response = _getAsyn(url); //?????同步？？？
                        is = response.body().byteStream();
                    }
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = inSampleSize;
                    final Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
                    mDelivery.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(bitmap);
                        }
                    });
                } catch (Exception e) {
                    setErrorResId(view, errorImageID);
                } finally {
                    if (is != null)
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        });
    }

    private void setErrorResId(final ImageView view, final int errorResId) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                view.setImageResource(errorResId);
            }
        });
    }

    //*************对外公布的方法************

    public static Response getAsyn(String url) throws IOException {
        return getInstance()._getAsyn(url);
    }

    public static String getAsString(String url) throws IOException {
        return getInstance()._getAsString(url);
    }

    public static void getAsyn(String url, ResultCallback callback) {
        getInstance()._getAsyn(url, callback);
    }

    public static Response post(String url, Param... params) throws IOException {
        return getInstance()._post(url, params);
    }

    public static String postAsString(String url, Param... params) throws IOException {

        return getInstance()._postAsString(url, params);
    }

    public static void postAsyn(String url, ResultCallback callback, Param... params) throws IOException {
        getInstance()._postAsyn(url, callback, params);
    }

    public static void postAsyn(String url, ResultCallback callback, Map<String, String> params) throws IOException {
        getInstance()._postAsyn(url, callback, params);
    }


    public static Response post(String url, File[] files, String[] fileKeys, Param... params) throws IOException {
        return getInstance()._post(url, files, fileKeys, params);
    }

    public static Response post(String url, File file, String fileKey) throws IOException {
        return getInstance()._post(url, file, fileKey);
    }

    public static Response post(String url, File file, String fileKey, Param... params) throws IOException {
        return getInstance()._post(url, file, fileKey, params);
    }

    public static void postAsyn(String url, ResultCallback callback, File[] files, String[] fileKeys, Param... params) throws IOException {
        getInstance()._postAsyn(url, callback, files, fileKeys, params);
    }


    public static void postAsyn(String url, ResultCallback callback, File file, String fileKey) throws IOException {
        getInstance()._postAsyn(url, callback, file, fileKey);
    }


    public static void postAsyn(String url, ResultCallback callback, File file, String fileKey, Param... params) throws IOException {
        getInstance()._postAsyn(url, callback, file, fileKey, params);
    }

    public static void downloadAsyn(String url, String destDir, ResultCallback callback) {
        getInstance()._downloadAsyn(url, destDir, callback);
    }

    public static void displayImage(final ImageView view, String url, int errorResId) throws IOException {
        getInstance()._displayImage(view, url, errorResId);
    }

    public static void displayImage(final ImageView view, String url) {
        getInstance()._displayImage(view, url, -1);
    }


    // ##########################################
    private Request buildMultipartFormRequest(String url, File[] files,
                                              String[] fileKeys, Param[] params) {
        params = validateParam(params);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for (Param param : params) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                    RequestBody.create(null, param.value));
        }
        if (files != null) {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //根据文件名设置contentType
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""), fileBody);

            }
        }
        MultipartBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    /**
     * 验证Param使其不为null
     *
     * @param params
     * @return
     */
    private Param[] validateParam(Param[] params) {
        if (params == null)
            return new Param[0];
        else return params;
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * 下载失败
     * 使用handler的发送到UI线程
     *
     * @param request
     * @param e
     * @param callback
     */
    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onError(request, e);
            }
        });
    }

    /**
     * 下载成功
     * 使用handler的发送到UI线程
     *
     * @param object
     * @param callback
     */
    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(object);
                }
            }
        });
    }

    /**
     * 创建结果回调并进行解析
     *
     * @param <T>
     */
    public static abstract class ResultCallback<T> {
        //泛型反射原理进行解析
        Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(T response);
    }

    /**
     * 创建Post请求的Request对象
     *
     * @param url
     * @param params
     * @return
     */
    private Request buildPostRequest(String url, Param[] params) {
        if (params == null) {
            params = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        FormBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    /**
     * 将map转换成Param...
     *
     * @param params
     * @return
     */
    private Param[] map2Params(Map<String, String> params) {
        if (params == null)
            return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        //使用foreach循环读取entrys的内容，并添加到Param[]中
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    /**
     * 抽象参数类型，一般使用在Post请求中
     */
    public static class Param {
        String key;
        String value;

        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
