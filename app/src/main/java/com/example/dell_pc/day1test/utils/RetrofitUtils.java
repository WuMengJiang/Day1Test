package com.example.dell_pc.day1test.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public class RetrofitUtils {
        private static RetrofitUtils httpUtils = null;
            private final MyService myService;

            private RetrofitUtils(String url) {
                OkHttpClient build = new OkHttpClient.Builder()
                        .addInterceptor(new LoggingInterceptor())
                        .connectTimeout(1000 * 30, TimeUnit.MILLISECONDS)
                        .writeTimeout(1000 * 30, TimeUnit.MILLISECONDS)
                        .build();
                myService = new Retrofit.Builder()
                        .baseUrl(url)
                        .client(build)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build().create(MyService.class);
            }

            public static RetrofitUtils getHttpUtils(String url) {
                if (httpUtils == null) {
                    synchronized (RetrofitUtils.class) {
                        if (httpUtils == null) {
                            httpUtils = new RetrofitUtils(url);
                        }
                    }
                }
                return httpUtils;
            }

            public <T> RetrofitUtils doGet(String url, Map<String, Object> map, final CallBack<T> callBack) {
                myService.doGet(url, map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    String string = responseBody.string();
                                    Gson gson = new Gson();
                                    Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                                    Type[] actualTypeArguments = ((ParameterizedType)
                                            genericInterfaces[0]).getActualTypeArguments();
                                    Type actualTypeArgument = actualTypeArguments[0];
                                    T o = gson.fromJson(string, actualTypeArgument);
                                    callBack.onSuccess(o);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                callBack.onError(e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                return httpUtils;
            }

            public <T> RetrofitUtils doGetArray(String url, final ArrayCallBack callBack) {
                myService.doGetArray(url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    String string = responseBody.string();
                                    callBack.onSuccess(string);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                callBack.onError(e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                return httpUtils;
            }

            public <T> RetrofitUtils doGet(String url, final CallBack<T> callBack) {
                myService.doGet(url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    String string = responseBody.string();
                                    Gson gson = new Gson();
                                    Log.e("onNext", "onNext: " + string);
                                    Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                                    Type[] actualTypeArguments = ((ParameterizedType)
                                            genericInterfaces[0]).getActualTypeArguments();
                                    Type actualTypeArgument = actualTypeArguments[0];
                                    T o = gson.fromJson(string, actualTypeArgument);
                                    callBack.onSuccess(o);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                callBack.onError(e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                return httpUtils;
            }

            public <T> RetrofitUtils doPost(String url, Map<String, Object> map, final CallBack<T> callBack) {
                myService.doPost(url, map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    String string = responseBody.string();

                                    Gson gson = new Gson();

                                    Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                                    Type[] actualTypeArguments = ((ParameterizedType)
                                            genericInterfaces[0]).getActualTypeArguments();
                                    Type actualTypeArgument = actualTypeArguments[0];

                                    T o = gson.fromJson(string, actualTypeArgument);

                                    callBack.onSuccess(o);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                callBack.onError(e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                return httpUtils;
            }

            interface MyService {
                @POST
                @FormUrlEncoded
                Observable<ResponseBody> doPost(@Url String url, @FieldMap Map<String, Object> map);

                @GET
                Observable<ResponseBody> doGet(@Url String url, @QueryMap Map<String, Object> map);

                @GET
                Observable<ResponseBody> doGet(@Url String url);

                @GET
                Observable<ResponseBody> doGetArray(@Url String url);
            }

            class LoggingInterceptor implements Interceptor {

                private static final String TAG = "LoggingInterceptor";

                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request request = chain.request();//获取请求数据

                    //1.请求前--打印请求信息
                    long startTime = System.nanoTime();
                    Log.d(TAG, String.format("Sending request %s on %s%n%s",
                            request.url(), chain.connection(), request.headers()));

                    //2.网络请求
                    Response response = chain.proceed(request);

                    //3.网络响应后--打印响应信息
                    long endTime = System.nanoTime();
                    Log.d(TAG, String.format("Received response for %s in %.1fms%n%s",
                            response.request().url(), (endTime - startTime) / 1e6d, response.headers()));

                    return response;
                }
            }
}
