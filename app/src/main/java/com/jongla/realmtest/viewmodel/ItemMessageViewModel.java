package com.jongla.realmtest.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.jongla.realmtest.model.Message;
import com.squareup.picasso.Picasso;

/**
 * Created by mikesolomon on 13/10/16.
 */

public class ItemMessageViewModel extends BaseObservable {
    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }

    private Message message;

    public ItemMessageViewModel(Message message) {
        this.message = message;
    }

    @Bindable
    public String getName() {
        return message.getSender().getName();
    }


    @Bindable
    public String getMessage() {
        return message.getMessage();
    }

    @Bindable
    public String getAvatarURL() {
        return message.getSender().getAvatarURL();
    }

}
