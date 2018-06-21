package com.chatbot.bo.impl;

import com.chatbot.bo.FbGraphApiBo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatbot.rest.tx.FbGraphApiUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.apache.http.HttpHeaders.USER_AGENT;


/**
 * Created by sewwandiwi on 6/5/2018.
 */
@Service("fbGraphApiBo")
public class FbGraphApiBoImpl implements FbGraphApiBo{

    private String appToken="?access_token=EAAH9MX7nyTQBAJx4Ctr0UE1TRnD4smZCMJSKTGnGxfSoKUVJCDns0abhfoGDbGdxmXZBo86ZCdc9XXOzylD04ygwNPFkUZAgtoDAjG7YUbMnc4ZByuJbd19rbdXscqTAx2xEigLdHlRfdBdxUOoOcfx3yP03gIIgrWcz0wvZAlZC2aUJKwqjo2XZCmietuTaDZA03OcUQI4fpbgZDZD";


    //private RestClient fbGraphTemplate;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    private String url="https://graph.facebook.com/";

    @Override
    public FbGraphApiUser getFbUserName(String userId) throws IOException  {

        //String url = "http://www.google.com/search?q=httpClient";
        FbGraphApiUser fbGraphApiUser=new FbGraphApiUser();

       String fbgraphApiUrl=url+userId+appToken;
       System.out.println(fbgraphApiUrl);

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(fbgraphApiUrl);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return fbGraphApiUser;
    }
}
