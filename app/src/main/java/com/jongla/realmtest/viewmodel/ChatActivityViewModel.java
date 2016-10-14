package com.jongla.realmtest.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.jongla.realmtest.model.GroupMember;
import com.jongla.realmtest.model.Message;

import java.util.UUID;

import io.realm.Realm;
import io.realm.User;

/**
 * Created by mikesolomon on 13/10/16.
 */

public class ChatActivityViewModel extends BaseObservable {

    private static final String TAG = "MainViewModel";


    public ObservableBoolean hasText;

    final private Realm realm;

    private String messageTextValue;

    public ChatActivityViewModel(Realm realm) {
        hasText = new ObservableBoolean(Boolean.FALSE);
        this.realm = realm;
    }

    public void onClickSend(View view) {
        final Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setTimestamp(System.currentTimeMillis());
        message.setMessage(messageTextValue);
        Log.d("ChatActivityViewModel", "length of members="+realm.where(GroupMember.class).findAll().size());
        message.setSender(realm.where(GroupMember.class).equalTo("id", User.currentUser().getIdentity()).findFirst());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(message);
            }
        });
    }

    public TextWatcher getMessageTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                messageTextValue = charSequence.toString();
                hasText.set(charSequence.length() > 0 ? true : false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

}
