package com.spproject.xdclass.config;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TestMain {
    public static void main(String[] args){
        LocalDateTime localDateTime = LocalDateTime.of(2021,10,15,20,10);
        while (localDateTime.isBefore(LocalDateTime.now())){
        String url = "http://localhost:8080/schedule/test";
        String time = localDateTime.toString();
        String body ="{\"time\":\""+time+"\"}";
            try {
                System.out.println(sendHttpPost(url,body));
            } catch (Exception e) {
                e.printStackTrace();
            }
            localDateTime = localDateTime.plusMinutes(10);
        }


    }

    public static String sendHttpPost(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmYWN0b3J5SWQiOjIsImV4cCI6MTYzNDkxNDIwNywidXNlcklkIjozfQ.wWx1fwos3hmJLMH8NIrqvCTv2EFIdFRBMvNYc7tO7Rg");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(body));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseContent);
        response.close();
        httpClient.close();
        return responseContent;
    }
}
