package com.chatbot.controller;

import com.chatbot.bo.FbGraphApiBo;
import com.chatbot.bo.UserBo;
import com.chatbot.bo.WelcomeBo;
import com.chatbot.model.*;
import com.chatbot.model.repository.*;
import com.chatbot.rest.model.*;
import com.chatbot.rest.tx.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.chatbot.rest.rq.DialogflowRq;

import java.io.IOException;
import java.util.*;

import static java.lang.Math.round;


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

    @Autowired
    MessageResponseRespository messageResponseRespository;


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
        DBMessage message=new DBMessage();


        String intentName=rq.getQueryResult().getIntent().getDisplayName();
        String source=rq.getOriginalDetectIntentRequest().getSource();
        String[] tempIntentId=rq.getQueryResult().getIntent().getName().split("/");
        String dialogflowIntentId=tempIntentId[tempIntentId.length-1];
        String[] session=rq.getSession().split("/");
        String sessionId=session[session.length-1];
        System.out.println("sessionID:"+sessionId);
        System.out.println("sessionID:"+sessionId);

        List<DBPlatform> platformList=platformRepository.findAll();
        for (DBPlatform dbPlatform1:platformList){
            if(dbPlatform1.getPlatformName().equalsIgnoreCase(source)){
                dbPlatform=dbPlatform1;
            }
        }
        System.out.println("platformID:"+dbPlatform.getPlatformId());
        DBUser user1=userRespository.findBySessionId(sessionId);
        String senderId=rq.getOriginalDetectIntentRequest().getPayload().getSender().getId();

        if(user1==(null)){

            user.setPlatformId(dbPlatform.getPlatformId());
            user.setSessionId(sessionId);
            user.setSessionStartedTime(date);
            user.setPlatformUniqueUserId(rq.getOriginalDetectIntentRequest().getPayload().getSender().getId());

            user =userRespository.save(user);
            System.out.println("UserID:"+user.getUserId());

            if (dbPlatform.getPlatformId()==1){
                DBFbUser dbFbUser1=fbUserRespository.findByFbId(senderId);

                if(dbFbUser1==null){
                    FbGraphApiUser fbGraphApiUser=fbGraphApiBo.getFbUserName(senderId);
                    sender=fbGraphApiUser.getFirst_name();
                    // fbUser.setUserId(user.getUserId());
                    fbUser.setFbId(senderId);
                    fbUser.setFirstName(sender);
                    fbUser.setLastName(fbGraphApiUser.getLast_name());
                    System.out.println("sender name:"+fbGraphApiUser.getFirst_name());

                    fbUser=fbUserRespository.save(fbUser);
                }
            }
            message.setUserId(user.getUserId());
        }else {
            message.setUserId(user1.getUserId());
        }

        DBIntent intent= intentRespository.findByDialogflowIntentId(dialogflowIntentId);
        System.out.println("Intent:"+intent.getDisplayName());


        message.setIntentId(intent.getIntentId());
        message.setMessage(rq.getQueryResult().getQueryText());
        message.setMessageTimeStamp(date);
        message.setPlatformId(dbPlatform.getPlatformId());
        message.setSessionId(sessionId);


        message=messageRespository.save(message);



        List<DBIntentResponse> intentResponseList=intentResponseRepository.findByIntentId(intent.getIntentId());
        for (DBIntentResponse intentResponse:intentResponseList){

            DBMessageResponse messageResponse=new DBMessageResponse();
            messageResponse.setMessageId(message.getMessageId());
            messageResponse.setIntentId(intentResponse.getIntentId());
            messageResponse.setMessageResponseId(intentResponse.getResponseId());
            messageResponse.setPlatformId(dbPlatform.getPlatformId());
            messageResponse.setResponseId(intentResponse.getResponseId());
            messageResponse.setResponseTypeId(intentResponse.getResponseTypeId());
            messageResponse = messageResponseRespository.save(messageResponse);


            System.out.println("inlist:"+intentResponse.getIntentResponseId());
            if(intentResponse.getResponseTypeId().equals(Long.valueOf(1))){
                DBTextResponse textResponse=textResponseRespository.findByTextResponseId(intentResponse.getResponseId());
                if(intent.getDisplayName().equalsIgnoreCase("Default Welcome Intent")){
                    String greeting=welcomeBo.greetingText(rq.getOriginalDetectIntentRequest().getPayload().getSender().getId());
                    textResponse.setText(greeting + textResponse.getText());
                    System.out.println("text:"+textResponse.getText());
                }
                Text text=new Text();
                List<String> textList=new ArrayList<>();
                textList.add(textResponse.getText());
                text.setText(textList);
                System.out.println("text:"+textList.get(0));
                fulfillmentMessageText.setText(text);
                fulfillmentMessages.add(fulfillmentMessageText);
            }else if(intentResponse.getResponseTypeId().equals(Long.valueOf(2))){
                DBQuickRepliesResponse quickRepliesResponse=quickRepliesResponseRespository.findByQuickRepliesId(intentResponse.getResponseId());
                List<DBQuickRepliesButtons> buttonsList=quickRepliesButtonRespository.findByQuickReplyResponseId(quickRepliesResponse.getQuickRepliesId());
                QuickReplies quickReplies=new QuickReplies();
                quickReplies.setTitle(quickRepliesResponse.getTitle());
                System.out.println("QR:"+quickReplies.getTitle());
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
                System.out.println("card:"+ card.getTitle());
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

        rs.setFulfillmentMessages(fulfillmentMessages);
        rs.setFulfillmentText("Text");
        rs.setSource("java-webhook");

        System.out.println(rs);
        return rs;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/intentummary")
    public @ResponseBody
    IntentSummaryRs intentSummary( ){

        IntentSummaryRs rs=new IntentSummaryRs();
        //List<IntentPrecentage> intentPrecentageList=messageRespository.getIntentCount();
        List<DBIntent> intentList=intentRespository.findAll();
        Long messageCount=messageRespository.count();
        System.out.println(messageCount);
        List <IntentPrecentage> intentPrecentageList=new ArrayList<>();


        for (DBIntent intent:intentList){
            Long intentcount=messageRespository.countByIntentId(intent.getIntentId());
            int precentage=(int) Math.round((Double.valueOf(intentcount)/Double.valueOf(messageCount))*100);
            IntentPrecentage intentPrecentage=new IntentPrecentage();
            intentPrecentage.setIntentID(intent.getIntentId());
            intentPrecentage.setIntentName(intent.getDisplayName());
            intentPrecentage.setIntentPrecentage(precentage);
            System.out.println(intentcount + " "+intent.getDisplayName()+" "+precentage);
            intentPrecentageList.add(intentPrecentage);
        }
        rs.setIntentPrecentageList(intentPrecentageList);
        rs.setTotalQueries(messageCount);
        rs.setCode(1000);
        rs.setMessage("Success");
        return rs;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/listUser")
    public @ResponseBody
    ListFbUserRs listUser(@RequestParam(value = "q", defaultValue = "") String name){

       ListFbUserRs rs=new ListFbUserRs();
       List<FBUser> userList=new ArrayList<>();
       List<DBFbUser> dbFbUserList=new ArrayList<>();
       List<DBFbUser> dbFbUserList1=new ArrayList<>();
       if(name==""){
           dbFbUserList=fbUserRespository.findAll();
       }else {
           // dbFbUserList=fbUserRespository.findByFirstName(name);
           // dbFbUserList1=fbUserRespository.findByLastName(name);
           //dbFbUserList=fbUserRespository.findByFirstNameAndLastName(name);
           dbFbUserList=fbUserRespository.findByFirstNameContaining(name);
       }
       if(dbFbUserList.size()>0){
           for (DBFbUser fbUser:dbFbUserList){
               FBUser fbUser1=new FBUser();
               fbUser1.setName(fbUser.getFirstName()+ " "+fbUser.getLastName());
               fbUser1.setFbId(fbUser.getFbUserId());
               userList.add(fbUser1);
           }
       }
        /*if (dbFbUserList1.size() > 0) {
            for (DBFbUser fbUser : dbFbUserList1) {
                FBUser fbUser1 = new FBUser();
                fbUser1.setName(fbUser.getFirstName() + " " + fbUser.getLastName());
                fbUser1.setFbId(fbUser.getFbUserId());
                userList.add(fbUser1);
            }
        }*/
       rs.setFbUserList(userList);
        return rs;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/userMessage")
    public @ResponseBody
    UserMessageRs userMessage(@RequestParam(value = "q", defaultValue = "") Long fbId){
           UserMessageRs rs=new UserMessageRs();
           DBFbUser fbUser=fbUserRespository.findByFbUserId(fbId);
           List<DBUser> userList=userRespository.findByPlatformUniqueUserId(fbUser.getFbId());
           for (DBUser user:userList){
               List<DBMessage> messageList=messageRespository.findBySessionId(user.getSessionId());
               for (DBMessage message:messageList){
                   List<DBIntentResponse> intentResponseList=intentResponseRepository.findByIntentId(message.getIntentId());
               }
           }


        return rs;
    }
}
