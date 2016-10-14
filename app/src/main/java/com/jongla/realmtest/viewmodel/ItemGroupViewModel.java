package com.jongla.realmtest.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.jongla.realmtest.ChatActivity;
import com.jongla.realmtest.model.Group;
import com.squareup.picasso.Picasso;

/**
 * Created by mikesolomon on 13/10/16.
 */

public class ItemGroupViewModel extends BaseObservable {
    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }

    private Context context;
    private Group group;

    public ItemGroupViewModel(Context context, Group group) {
        this.context = context;
        this.group = group;
    }

    @Bindable
    public String getName() {
        return group.getName();
    }

    @Bindable
    public String getAvatarURL() {
        return group.getAvatarURL();
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(ChatActivity.GROUP_ID, group.getId());
        context.startActivity(intent);
    }

}
