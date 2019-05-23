package com.me.hyh;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

/**
 * @author yunhua.he
 * @date 2018/12/7
 */
public class HttpUtils {

    /**
     * GET请求,返回json
     * @param url
     * @return
     */
    public static JSONObject getHttp(String url)  {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            JSONObject json = JSONObject.parseObject(data);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * GET请求,返回string
     * @param url
     * @return
     */
    public static String getHttpString(String url)  {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * POST请求
     * @param url
     * @return
     */
    public static JSONObject postHttp(String url, JSONObject jsonObject)  {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, jsonObject.toJSONString())).build();
        JSONObject json;
        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            json = JSONObject.parseObject(data);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * PUT请求
     * @param url
     * @return
     */
    public static JSONObject putHttp(String url)  {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder().url(url).put(RequestBody.create(mediaType, "")).build();
        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            JSONObject object = JSONObject.parseObject(data);
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * delete请求
     * @param url
     * @return
     */
    public static JSONObject deleteHttp(String url)  {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).delete().build();
        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            return JSONObject.parseObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray getHttpArray(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            JSONArray array = JSONArray.parseArray(data);
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
