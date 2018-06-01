package hello;

import jdk.nashorn.internal.parser.JSONParser;
import model.rq.DialogFlowRq;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj){

        System.out.println(obj);
        String test= obj.substring(obj.indexOf("intentName")+1, obj.indexOf("fulfillment"));
        //Boolean word= obj.contains("intentName");
        String test2=test.substring(test.indexOf(":")+1, test.indexOf("}")).trim();
        String test3=test2.substring(1,test2.length()-1);
        System.out.println(test);
        System.out.println(test3);
        String greeting="Welcome";


        /*System.out.println(obj);
        System.out.println("*******");
        String test= obj.substring(obj.indexOf("intent"), obj.indexOf("intentDetectionConfidence"));
        //Boolean word= obj.contains("intent");
        String test2=test.substring(test.indexOf("displayName")+15, test.indexOf("}")).trim();
        String test3=test2.substring(0,test2.length()-1);
        //System.out.println(test);
        //System.out.println(test2);
        //System.out.println(test3);
        String greeting="Welcome";*/

        if(test3.equalsIgnoreCase("Default Welcome Intent")){

            GregorianCalendar time = new GregorianCalendar();
            if(time.get(Calendar.HOUR_OF_DAY)<12){
                greeting="Good Morning !";
            }else if(time.get(Calendar.HOUR_OF_DAY)>=12 && time.get(Calendar.HOUR_OF_DAY)<16){
                greeting="Good Afternoon !";
            }else {
                greeting="Good Evening !";
            }
            greeting=greeting+ ", Welcome to Mobitel Virtual Private Assistant. How Can I help You.";
        }
        return new WebhookResponse( greeting , "Text " );
    }
}
