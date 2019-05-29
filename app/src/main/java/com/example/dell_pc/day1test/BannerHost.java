package com.example.dell_pc.day1test;

public class BannerHost {
    private String url ;

    public BannerHost(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BannerHost{" +
                "url='" + url + '\'' +
                '}';
    }
}
