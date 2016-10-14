package com.jongla.realmtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jongla.realmtest.list.GroupListAdapter;
import com.jongla.realmtest.model.Group;
import com.jongla.realmtest.util.RealmManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.User;

/**
 * Created by mikesolomon on 13/10/16.
 */

public class GroupListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_list_activity);
        ButterKnife.bind(this);
        User user = User.currentUser();
        SyncConfiguration config = new SyncConfiguration.Builder(user, RealmTestApplication.USER_URL).build();
        realm = RealmManager.getInstance(config);
        RealmResults<Group> groups = realm.where(Group.class).findAll().sort("name");
        GroupListAdapter adapter = new GroupListAdapter(this, groups);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
