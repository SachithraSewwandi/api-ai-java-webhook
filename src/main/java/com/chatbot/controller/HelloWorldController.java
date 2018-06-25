package com.chatbot.controller;

import com.chatbot.bo.FbGraphApiBo;
import com.chatbot.bo.UserBo;
import com.chatbot.bo.WelcomeBo;
import com.chatbot.model.*;
import com.chatbot.model.repository.*;
import com.chatbot.rest.model.*;
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
import java.util.*;


@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

    @Autowired
    FbGraphApiBo fbGraphApiBo;

    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    UserRespository userRespository;

    @Autowired
    FbUserRespository fbUserRespository;

    @Autowired
    CardButtonRespository cardButtonRespository;

    @Autowired
    CardResponseRespository cardResponseRespository;

    @Autowired
    FeedBackRespository feedBackRespository;

    @Autowired
    IntentResponseRepository intentResponseRepository;

    @Autowired
    IntentRespository intentRespository;

    @Autowired
    MessageRespository messageRespository;

    @Autowired
    QuickRepliesButtonRespository quickRepliesButtonRespository;

    @Autowired
    QuickRepliesResponseRespository quickRepliesResponseRespository;

    @Autowired
    ResponseTypeRespository responseTypeRespository;

    @Autowired
    TextResponseRespository textResponseRespository;

    @Autowired
    WelcomeBo welcomeBo;


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody DialogflowRs webhook(
            @RequestBody DialogflowRq rq) throws IOException {

       // System.out.println(dataSource);
        Date date=new Date();
        DBUser user=new DBUser();
        DBFbUser fbUser=new DBFbUser();
        DBPlatform dbPlatform=new DBPlatform();
        String sender="";
        //Long platformId=Long.valueOf(0);
        //Long userId=Long.valueOf(0);
        System.out.println(rq);
        System.out.println(rq.getQueryResult().getIntent().getDisplayName());
        //String message="";

        //response materials
        DialogflowRs rs=new DialogflowRs();
        FulfillmentMessageQuickReply fulfillmentMessageQuickReply=new FulfillmentMessageQuickReply();
        FulfillmentMessageText fulfillmentMessageText=new FulfillmentMessageText();
        FulfillmentMessageCard fulfillmentMessageCard=new FulfillmentMessageCard();
        List<Object> fulfillmentMessages=new ArrayList<Object>();


        String intentName=rq.getQueryResult().getIntent().getDisplayName();
        String source=rq.getOriginalDetectIntentRequest().getSource();
        String[] tempIntentId=rq.getQueryResult().getIntent().getName().split("/");
        String dialogflowIntentId=tempIntentId[tempIntentId.length-1];
        String[] session=rq.getSession().split("/");
        String sessionId=session[session.length-1];
        System.out.println("sessionID:"+sessionId);

        List<DBPlatform> platformList=platformRepository.findAll();
        for (DBPlatform dbPlatform1:platformList){
            if(dbPlatform1.getPlatformName().equalsIgnoreCase(source)){
                dbPlatform=dbPlatform1;
            }
        }
        System.out.println("platformID:"+dbPlatform.getPlatformId());
        DBUser user1=userRespository.findBySessionId(sessionId);


        if(user1==(null)){

            user.setPlatformId(dbPlatform.getPlatformId());
            user.setSessionId(sessionId);
            user.setSessionStartedTime(date);

            user =userRespository.save(user);
            System.out.println("UserID:"+user.getUserId());

            if (dbPlatform.getPlatformId()==1){
                String senderId=rq.getOriginalDetectIntentRequest().getPayload().getSender().getId();
                FbGraphApiUser fbGraphApiUser=fbGraphApiBo.getFbUserName(senderId);
                sender=fbGraphApiUser.getFirst_name();
                fbUser.setUserId(user.getUserId());
                fbUser.setFbId(senderId);
                fbUser.setFirstName(sender);
                fbUser.setLast_name(fbGraphApiUser.getLast_name());

                fbUser=fbUserRespository.save(fbUser);

            }

        }

        DBIntent intent= intentRespository.findByDialogflowIntentId(dialogflowIntentId);
        System.out.println("Intent:"+intent.getDisplayName());

        DBMessage message=new DBMessage();
        message.setIntentId(intent.getIntentId());
        message.setMessage(rq.getQueryResult().getQueryText());
        message.setMessageTimeStamp(date);
        message.setPlatformId(dbPlatform.getPlatformId());
        message.setSessionId(sessionId);
        message.setUserId(user1.getUserId());

        message=messageRespository.save(message);



        List<DBIntentResponse> intentResponseList=intentResponseRepository.findByIntentId(intent.getIntentId());
        for (DBIntentResponse intentResponse:intentResponseList){
            if(intentResponse.getResponseTypeId().equals(Long.valueOf(1))){
                DBTextResponse textResponse=textResponseRespository.findByTextResponseId(intentResponse.getResponseId());
                if(intent.getDisplayName().equalsIgnoreCase("Default Welcome Intent")){
                    String greeting=welcomeBo.greetingText(rq.getOriginalDetectIntentRequest().getPayload().getSender().getId());
                    textResponse.setText(greeting + textResponse.getText());
                }
                Text text=new Text();
                List<String> textList=new ArrayList<>();
                textList.add(textResponse.getText());
                text.setText(textList);
                fulfillmentMessageText.setText(text);
                fulfillmentMessages.add(fulfillmentMessageText);
            }else if(intentResponse.getResponseTypeId().equals(Long.valueOf(2))){
                DBQuickRepliesResponse quickRepliesResponse=quickRepliesResponseRespository.findByQuickRepliesId(intentResponse.getResponseId());
                List<DBQuickRepliesButtons> buttonsList=quickRepliesButtonRespository.findByQuickReplyResponseId(quickRepliesResponse.getQuickRepliesId());
                QuickReplies quickReplies=new QuickReplies();
                quickReplies.setTitle(quickRepliesResponse.getTitle());
                List <String> quickrepliesList=new ArrayList<>();
                for (DBQuickRepliesButtons buttons:buttonsList){
                    quickrepliesList.add(buttons.getQuickReplyTitle());
                }
                quickReplies.setQuickReplies(quickrepliesList);
                fulfillmentMessageQuickReply.setQuickReplies(quickReplies);
                fulfillmentMessages.add(fulfillmentMessageQuickReply);
            }else if(intentResponse.getResponseTypeId().equals(Long.valueOf(3))){
                DBCardResponse cardResponse=cardResponseRespository.findByCardResponseId(intentResponse.getResponseId());
                List<DBCardButtons> buttonsList=cardButtonRespository.findByCardResponseId(cardResponse.getCardResponseId());
                Card card=new Card();
                card.setTitle(cardResponse.getTitle());
                card.setSubtitle(cardResponse.getSubtitle());
                List <Buttons> cardbuttonList=new ArrayList<>();
                for (DBCardButtons buttons:buttonsList){
                    Buttons buttons1=new Buttons();
                    buttons1.setText(buttons.getCardButtonText());
                    buttons1.setPostback(buttons.getCardButtonPostbackUrl());
                    cardbuttonList.add(buttons1);
                }

                card.setButtons(cardbuttonList);
                fulfillmentMessageCard.setCard(card);
                fulfillmentMessages.add(fulfillmentMessageCard);
            }


        }

        /*if(intentName.equalsIgnoreCase("Default Welcome DBIntent")){

            if(source.equals("facebook")){
                String senderId=rq.getOriginalDetectIntentRequest().getPayload().getSender().getId();
                FbGraphApiUser fbGraphApiUser=fbGraphApiBo.getFbUserName(senderId);
                sender=fbGraphApiUser.getFirst_name();
                //System.out.println("Sender:" +sender);
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

            DBPlatform platform=platformRepository.findByPlatformId(Long.valueOf(1));
            System.out.println("******"+platform.getPlatformName());

        }*/
        /*fulfillmentMessages.add(fulfillmentMessageText);
        fulfillmentMessages.add(fulfillmentMessageQuickReply);*/

        rs.setFulfillmentMessages(fulfillmentMessages);
        rs.setFulfillmentText("Text");
        rs.setSource("java-webhook");

        System.out.println(rs);
        return rs;
    }


}
