package com.chatbot.controller;

import com.chatbot.bo.FbGraphApiBo;
import com.chatbot.bo.UserBo;
import com.chatbot.bo.WelcomeBo;
import com.chatbot.model.*;
import com.chatbot.model.repository.*;
import com.chatbot.rest.model.*;
import com.chatbot.rest.rq.UserMessageRq;
import com.chatbot.rest.tx.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.chatbot.rest.rq.DialogflowRq;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Math.round;

@CrossOrigin
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

            if (dbPlatform.getPlatformId().equals(Long.valueOf(1))){
                DBFbUser dbFbUser1=fbUserRespository.findByFbId(senderId);

                if(dbFbUser1==null){
                    FbGraphApiUser fbGraphApiUser=fbGraphApiBo.getFbUserName(senderId);
                    sender=fbGraphApiUser.getFirst_name();
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
            messageResponse.setResponseTypeId(intentResponse.getResponseId());
            messageResponse.setPlatformId(dbPlatform.getPlatformId());
            messageResponse.setResponseId(intentResponse.getResponseId());
            messageResponse.setResponseTypeId(intentResponse.getResponseTypeId());
            messageResponse.setSessionId(sessionId);
            messageResponse.setTimeStamp(date);
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

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/listUser")
    public @ResponseBody
    ListFbUserRs listUser(
                          @RequestParam(value = "datatable[sort][field]" ,defaultValue = "firstName") final String e,
                          @RequestParam(value = "datatable[sort][sort]" ,defaultValue = "asc") final String f,
                          @RequestParam(name = "datatable[query][generalSearch]", defaultValue = "") String name,
                          @RequestParam(name = "draw", defaultValue = "2") Integer d,
                          @RequestParam(name = "datatable[pagination][perpage]", defaultValue = "10") final Integer length,
                          @RequestParam(name = "datatable[pagination][page]", defaultValue = "2") final Integer start, final Pageable p){

       ListFbUserRs rs=new ListFbUserRs();

        Pageable page = new Pageable() {
            @Override
            public int getPageNumber() {
                return p.getPageNumber();
            }

            @Override
            public int getPageSize() {
                return length;
            }

            @Override
            public int getOffset() {
                return (start - 1)*length;
            }

            @Override
            public Sort getSort() {
                return new Sort(f.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, e);
                //return p.getSort();
            }

            @Override
            public Pageable next() {
                return p.next();
            }

            @Override
            public Pageable previousOrFirst() {
                return p.previousOrFirst();
            }

            @Override
            public Pageable first() {
                return p.first();
            }

            @Override
            public boolean hasPrevious() {
                return p.hasPrevious();
            }
        };

        Page<DBFbUser> data=fbUserRespository.find(name, page);

        rs.getMeta().put("page",start);
        rs.getMeta().put("pages",data.getTotalPages());
        rs.getMeta().put("perpage",length);
        rs.getMeta().put("total",data.getTotalElements());

        //rs.getData().addAll(data.getContent());
        List<FBUser> fbUserList=new ArrayList<>();
        for (DBFbUser user:data.getContent()){
            FBUser fbUser=new FBUser();
            fbUser.setName(user.getFirstName()+" "+user.getLastName());
            fbUser.setFbId(user.getFbUserId());
            fbUserList.add(fbUser);
        }
        rs.setData(fbUserList);

       /*List<FBUser> userList=new ArrayList<>();
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
        *//*if (dbFbUserList1.size() > 0) {
            for (DBFbUser fbUser : dbFbUserList1) {
                FBUser fbUser1 = new FBUser();
                fbUser1.setName(fbUser.getFirstName() + " " + fbUser.getLastName());
                fbUser1.setFbId(fbUser.getFbUserId());
                userList.add(fbUser1);
            }
        }*/
       //rs.setFbUserList(userList);
        return rs;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/userMessage")
    public @ResponseBody
    UserMessageRs userMessage(@RequestBody UserMessageRq rq){
           Long fbId=rq.getFbId();
           UserMessageRs rs=new UserMessageRs();
           List<UserMessage> userMessageList=new ArrayList<>();
           DBFbUser fbUser=fbUserRespository.findByFbUserId(fbId);
           List<DBUser> userList=userRespository.findByPlatformUniqueUserId(fbUser.getFbId());
           for (DBUser user:userList){
               List<DBMessage> messageList=messageRespository.findBySessionId(user.getSessionId());

               for (DBMessage message:messageList){

                   UserMessage userMessage=new UserMessage();
                   List<String> userMessageList1=new ArrayList<>();
                   userMessage.setUserName(fbUser.getFirstName());
                   userMessageList1.add(message.getMessage());
                   userMessage.setUserMessage(userMessageList1);
                   userMessage.setTimeStamp(message.getMessageTimeStamp());
                   userMessageList.add(userMessage);

                   List<DBMessageResponse> messageResponseList=messageResponseRespository.findByMessageId(message.getMessageId());
                   List<String> responsetextList=new ArrayList<>();
                   UserMessage responseMessage=new UserMessage();
                   for (DBMessageResponse messageResponse:messageResponseList){

                       responseMessage.setUserName("Mobitel");

                       if(messageResponse.getResponseTypeId().equals(Long.valueOf(1))){
                           DBTextResponse textResponse=textResponseRespository.findByTextResponseId(messageResponse.getResponseId());
                           //Text text=new Text();

                           responsetextList.add("Text : "+textResponse.getText());
                           // text.setText(textList);
                           //System.out.println("text:"+textList.get(0));
                           //fulfillmentMessageText.setText(text);
                           //fulfillmentMessages.add(fulfillmentMessageText);
                       }else if(messageResponse.getResponseTypeId().equals(Long.valueOf(2))){
                           DBQuickRepliesResponse quickRepliesResponse=quickRepliesResponseRespository.findByQuickRepliesId(messageResponse.getResponseId());
                           List<DBQuickRepliesButtons> buttonsList=quickRepliesButtonRespository.findByQuickReplyResponseId(quickRepliesResponse.getQuickRepliesId());
                           responsetextList.add("Quick Reply Title: "+quickRepliesResponse.getTitle());
                           //QuickReplies quickReplies=new QuickReplies();
                           //quickReplies.setTitle(quickRepliesResponse.getTitle());
                           //System.out.println("QR:"+quickReplies.getTitle());
                           List <String> quickrepliesList=new ArrayList<>();
                           int count=0;
                           for (DBQuickRepliesButtons buttons:buttonsList){
                               count ++;
                               //quickrepliesList.add(buttons.getQuickReplyTitle());
                               responsetextList.add("Quick Reply Button "+count+" Title :"+buttons.getQuickReplyTitle());
                           }
                           //quickReplies.setQuickReplies(quickrepliesList);
                           //fulfillmentMessageQuickReply.setQuickReplies(quickReplies);
                           //fulfillmentMessages.add(fulfillmentMessageQuickReply);
                       }else if(messageResponse.getResponseTypeId().equals(Long.valueOf(3))){
                           DBCardResponse cardResponse=cardResponseRespository.findByCardResponseId(messageResponse.getResponseId());
                           List<DBCardButtons> buttonsList=cardButtonRespository.findByCardResponseId(cardResponse.getCardResponseId());
                           //Card card=new Card();
                           //card.setTitle(cardResponse.getTitle());
                           responsetextList.add("Card Title: " +cardResponse.getTitle());
                           //System.out.println("card:"+ card.getTitle());
                           //card.setSubtitle(cardResponse.getSubtitle());
                           responsetextList.add("Card Subtitle: "+cardResponse.getSubtitle());
                           List <Buttons> cardbuttonList=new ArrayList<>();
                           int count=0;
                           for (DBCardButtons buttons:buttonsList){
                               count ++;
                           /*Buttons buttons1=new Buttons();
                           buttons1.setText(buttons.getCardButtonText());
                           buttons1.setPostback(buttons.getCardButtonPostbackUrl());
                           cardbuttonList.add(buttons1);*/
                               responsetextList.add("Card Button " +count +" Title:" +buttons.getCardButtonText());
                               responsetextList.add("Card Button " +count +" Postback URL:" +buttons.getCardButtonPostbackUrl());
                           }
                       }
                   }
                   responseMessage.setUserMessage(responsetextList);
                   responseMessage.setTimeStamp(message.getMessageTimeStamp());
                   userMessageList.add(responseMessage);


               }
           }
           rs.setUserMessageList(userMessageList);


        return rs;
    }
}
