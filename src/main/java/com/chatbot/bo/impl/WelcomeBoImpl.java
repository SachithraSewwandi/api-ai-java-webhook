package com.chatbot.bo.impl;

import com.chatbot.bo.FbGraphApiBo;
import com.chatbot.bo.WelcomeBo;
import com.chatbot.rest.tx.FbGraphApiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by sewwandiwi on 6/24/2018.
 */
@Service("welcomeBo")
public class WelcomeBoImpl implements WelcomeBo {

    @Autowired
    FbGraphApiBo fbGraphApiBo;

    @Override
    public String greetingText(String senderid) throws IOException {
        String message="Hi ";

        FbGraphApiUser fbGraphApiUser=fbGraphApiBo.getFbUserName(senderid);
        String sender=fbGraphApiUser.getFirst_name();
            //System.out.println("Sender:" +sender);
        if(fbGraphApiUser.getFirst_name()!=null){
            message=message +fbGraphApiUser.getFirst_name() +",";
        }
        TimeZone timeZone=TimeZone.getTimeZone("Asia/Colombo");
        GregorianCalendar time = new GregorianCalendar();
        time.setTimeZone(timeZone);

        if(time.get(Calendar.HOUR_OF_DAY)<12){
            message=message+"Good Morning ! ";
        }else if(time.get(Calendar.HOUR_OF_DAY)>=12 && time.get(Calendar.HOUR_OF_DAY)<16){
            message=message+"Good Afternoon ! ";
        }else {
            message=message+"Good Evening ! ";
        }

        return message;
    }
}
