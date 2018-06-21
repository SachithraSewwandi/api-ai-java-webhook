package com.chatbot.controller;

import com.chatbot.bo.FbGraphApiBo;
import com.chatbot.model.FulfillmentMessage;
import com.chatbot.model.QueryResult;
import com.chatbot.model.QuickReplies;
import com.chatbot.rest.tx.DialogflowRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chatbot.rest.rq.DialogflowRq;
import com.chatbot.rest.tx.FbGraphApiUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

    @Autowired
    FbGraphApiBo fbGraphApiBo;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody DialogflowRs webhook(
            @RequestBody DialogflowRq rq) throws IOException {

        System.out.println(rq);
        System.out.println(rq.getQueryResult().getIntent().getDisplayName());

        String message="";
        String intentName=rq.getQueryResult().getIntent().getDisplayName();
        String source=rq.getOriginalDetectIntentRequest().getSource();
        DialogflowRs rs=new DialogflowRs();
        FulfillmentMessage fulfillmentMessage=new FulfillmentMessage();
        List<FulfillmentMessage> fulfillmentMessages=new ArrayList<FulfillmentMessage>();



        if(intentName.equalsIgnoreCase("Default Welcome Intent")){

            String senderName="";


            if(source.equals("facebook")){
                String senderId=rq.getOriginalDetectIntentRequest().getPayload().getSender().getId();
                FbGraphApiUser fbGraphApiUser=fbGraphApiBo.getFbUserName(senderId);
                senderName=fbGraphApiUser.getFirst_name();
                System.out.println(senderName);
            }

            GregorianCalendar time = new GregorianCalendar();
            if(time.get(Calendar.HOUR_OF_DAY)<12){
                message="Good Morning !";
            }else if(time.get(Calendar.HOUR_OF_DAY)>=12 && time.get(Calendar.HOUR_OF_DAY)<16){
                message="Good Afternoon !";
            }else {
                message="Good Evening !";
            }

            //FbGraphApiUser fbGraphApiUser=fbGraphApiBo.getFbUserName();
            message=message+ ", Welcome to Mobitel Virtual Private Assistant. How Can I help You.";
        }else if(intentName.equalsIgnoreCase("Contact Agent")){

            message="hi, How can I assist you";
        }else if(intentName.equalsIgnoreCase("Mobitel Upahara")){
            QuickReplies quickReplies=new QuickReplies();
            quickReplies.setTitle("Select an option from below, please scroll horizontally for more options");
            List<String> qr=new ArrayList<>();
            qr.add("New Upahara");
            qr.add("Meth Garu Saru");
            quickReplies.setQuickReplies(qr);

            fulfillmentMessage.setQuickReplies(quickReplies);
        }
        fulfillmentMessages.add(fulfillmentMessage);
        QueryResult queryResult=new QueryResult();
        queryResult.setFulfillmentMessages(fulfillmentMessages);
        rs.setQueryResult(queryResult);
        System.out.println(rs);
        return rs;
    }
}
