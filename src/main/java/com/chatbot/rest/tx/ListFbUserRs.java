package com.chatbot.rest.tx;

import com.chatbot.model.DBFbUser;
import com.chatbot.rest.model.FBUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sewwandiwi on 6/26/2018.
 */
public class ListFbUserRs {

   private List<DBFbUser> data;
   private Map<String, Object> meta=new HashMap<String, Object>(0);

    public List<DBFbUser> getData() {
        return data;
    }

    public void setData(List<DBFbUser> data) {
        this.data = data;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }
}
