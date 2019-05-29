package com.example.dell_pc.day1test.utils;

public  interface CallBack<T> {
    void onSuccess(T o);

    void onError(String s);
}
