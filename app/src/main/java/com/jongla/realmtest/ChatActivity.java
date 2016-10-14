package com.jongla.realmtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.jongla.realmtest.databinding.ChatActivityBinding;
import com.jongla.realmtest.list.MessageListAdapter;
import com.jongla.realmtest.model.GroupMember;
import com.jongla.realmtest.model.Message;
import com.jongla.realmtest.util.RealmManager;
import com.jongla.realmtest.viewmodel.ChatActivityViewModel;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.User;

/**
 * Created by mikesolomon on 13/10/16.
 */

public class ChatActivity extends AppCompatActivity {

    static public final String GROUP_ID = "GROUP_ID";

    Realm groupRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // realm
        User user = User.currentUser();
        String groupId = getIntent().getStringExtra(GROUP_ID);
        String[] groupIdSplit = groupId.split("/");
        SyncConfiguration config =
                new SyncConfiguration.Builder(user, RealmTestApplication.makeGroupUrl(groupIdSplit[0], groupIdSplit[1])).build();
        groupRealm = RealmManager.getInstance(config);
        RealmResults<Message> messages = groupRealm.where(Message.class).findAll().sort("timestamp");
        RealmResults<GroupMember> members = groupRealm.where(GroupMember.class).findAll();
        Log.d("ChatActivity", "config="+config.getServerUrl().toString()+" nmembers="+members.size());
        MessageListAdapter adapter = new MessageListAdapter(this, messages);

        // android
        ChatActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.chat_activity);
        ChatActivityViewModel viewModel = new ChatActivityViewModel(groupRealm);
        binding.setViewModel(viewModel);
        binding.chatListView.setAdapter(adapter);
        binding.chatListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
