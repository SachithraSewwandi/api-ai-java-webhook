package com.chatbot.restTemplate;

import org.springframework.http.HttpStatus;

/**
 * Created by hp m6 on 12/1/2015.
 *
 * @author Chamika Dilshan
 */

public class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}