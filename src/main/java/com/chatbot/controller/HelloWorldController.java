package com.chatbot.controller;

import com.chatbot.bo.FbGraphApiBo;
import com.chatbot.model.*;
import com.chatbot.rest.tx.DialogflowRs;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        //Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        String intentName=rq.getQueryResult().getIntent().getDisplayName();
        String source=rq.getOriginalDetectIntentRequest().getSource();
        DialogflowRs rs=new DialogflowRs();
        FulfillmentMessageQuickReply fulfillmentMessageQuickReply=new FulfillmentMessageQuickReply();
        FulfillmentMessageText fulfillmentMessageText=new FulfillmentMessageText();
        List<Object> fulfillmentMessages=new ArrayList<Object>();
        /*HttpResponseMessage response = new HttpResponseMessage();
        response.Content.Headers.ContentType = new MediaTypeHeaderValue("application/json");*/
        String res="";



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
            //fulfillmentMessage.setPlatform("FACEBOOK");
            fulfillmentMessageQuickReply.setQuickReplies(quickReplies);

            Text text=new Text();
            List<String> textResponse=new ArrayList<>();
            textResponse.add("asdfghjkl");
            text.setText(textResponse);
            fulfillmentMessageText.setText(text);

            //String jsonInString = "quickReplies" +":" + mapper.writeValueAsString(quickReplies);
            //jsonInString=jsonInString.replace("\"","");
            //System.out.println(jsonInString);
            //String textstring =  "text" +":" + mapper.writeValueAsString(text);
            //System.out.println(textstring);
            //System.out.println( );
            //res="[ {"+ textstring+"," +jsonInString +"}]";
            //System.out.println(res);

            /*fulfillmentMessage.setQuickReplies(quickReplies);
            fulfillmentMessage1.setText(text);*/
        }
        fulfillmentMessages.add(fulfillmentMessageText);
        fulfillmentMessages.add(fulfillmentMessageQuickReply);

        rs.setFulfillmentMessages(fulfillmentMessages);
        rs.setFulfillmentText("Text");
        rs.setSource("java-webhook");
        //QueryResult queryResult=new QueryResult();
        //rq.getQueryResult().setFulfillmentMessages(fulfillmentMessages);
        //rq.setQueryResult(queryResult);
        System.out.println(rs);
        return rs;
    }
}
