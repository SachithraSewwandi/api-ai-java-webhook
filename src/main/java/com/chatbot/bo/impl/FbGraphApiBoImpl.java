package com.chatbot.bo.impl;

import com.chatbot.bo.FbGraphApiBo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.chatbot.rest.tx.FbGraphApiUser;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.apache.http.HttpHeaders.USER_AGENT;


/**
 * Created by sewwandiwi on 6/5/2018.
 */
@Service("fbGraphApiBo")
public class FbGraphApiBoImpl implements FbGraphApiBo{

    private String appToken="?access_token=EAAH9MX7nyTQBAIiuiOdZBqI3HFACErt1DzJkwnLZA4WVF5dOyYm0AM2z3A9hsOsgZA80RzAsGHyUtqvNHXU1mZArLL9ixD56htlchRr1jd6gtpNmVcp20qj4bvzi5qmKZAnlgx48P6UbZAShWMDGcjeEdQ4ZCHNBcGFYLeeZBg7zPAZDZD";


    //private RestClient fbGraphTemplate;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    private String url="https://graph.facebook.com/";

    @Override
    public FbGraphApiUser getFbUserName(String userId) throws IOException  {

        //String url = "http://www.google.com/search?q=httpClient";
       // FbGraphApiUser fbGraphApiUser=new FbGraphApiUser();

       String fbgraphApiUrl=url+userId+appToken;
       //System.out.println(fbgraphApiUrl);

        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        // HttpEntity<String>: To get result as String.
        HttpEntity<FbGraphApiUser> entity = new HttpEntity<FbGraphApiUser>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<FbGraphApiUser> response = restTemplate.exchange(fbgraphApiUrl, //
                HttpMethod.GET, entity, FbGraphApiUser.class);

        FbGraphApiUser fbGraphApiUser = response.getBody();



        return fbGraphApiUser;
    }
}
