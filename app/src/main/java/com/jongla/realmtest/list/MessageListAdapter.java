/*
 * Copyright 2016 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jongla.realmtest.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jongla.realmtest.R;
import com.jongla.realmtest.databinding.ItemMessageBinding;
import com.jongla.realmtest.model.Message;
import com.jongla.realmtest.viewmodel.ItemMessageViewModel;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MessageListAdapter extends RealmRecyclerViewAdapter<Message, MessageListAdapter.MessageListAdapterViewHolder> {

    public MessageListAdapter(Context context, OrderedRealmCollection<Message> items) {
        super(context, items, true);
    }

    @Override
    public MessageListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMessageBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_message,
                parent,
                false);
        return new MessageListAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MessageListAdapterViewHolder holder, int position) {
        holder.bindMessage(getData().get(position));
    }

    public static class MessageListAdapterViewHolder extends RecyclerView.ViewHolder {
        final ItemMessageBinding binding;

        public MessageListAdapterViewHolder(ItemMessageBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindMessage(Message message) {
            binding.setViewModel(new ItemMessageViewModel(message));
        }
    }
}
