package com.chatbot.restTemplate;

/**
 * Created by hp m6 on 11/24/2015.
 *
 * @author Chamika Dilshan
 */

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class RestClient {


    private String host;
    private int port;

    private String username;
    private String password;

    private RestTemplate restTemplate;

    public RestClient(){

    }

    public RestClient(String password, String host, int port, String username) {
        this.password = password;
        this.host = host;
        this.port = port;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void initRestTemplate() {
        HttpHost host = new HttpHost(getHost(), getPort(), "http");
        CloseableHttpClient client = HttpClientBuilder.create().
                setDefaultCredentialsProvider(provider()).build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactoryDigestAuth(host, client);

        restTemplate = new RestTemplate(requestFactory);
        restTemplate.setErrorHandler(new MyResponseErrorHandler());
    }

    private CredentialsProvider provider() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials("admin1", "admin1Pass");
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }


}
