package com.jongla.realmtest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by mikesolomon on 13/10/16.
 */

public class Message extends RealmObject {

    @PrimaryKey
    private String id;
    private GroupMember sender;
    @Required
    private String message;
    @Required
    private Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GroupMember getSender() {
        return sender;
    }

    public void setSender(GroupMember sender) {
        this.sender = sender;
    }


}
