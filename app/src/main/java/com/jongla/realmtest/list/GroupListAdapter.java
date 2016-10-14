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
import com.jongla.realmtest.databinding.ItemGroupBinding;
import com.jongla.realmtest.model.Group;
import com.jongla.realmtest.viewmodel.ItemGroupViewModel;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class GroupListAdapter extends RealmRecyclerViewAdapter<Group, GroupListAdapter.GroupListAdapterViewHolder> {

    public GroupListAdapter(Context context, OrderedRealmCollection<Group> items) {
        super(context, items, true);
    }

    @Override
    public GroupListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGroupBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_group,
                parent,
                false);
        return new GroupListAdapterViewHolder(context, binding);
    }

    @Override
    public void onBindViewHolder(GroupListAdapterViewHolder holder, int position) {
        holder.bindGroup(getData().get(position));
    }

    public static class GroupListAdapterViewHolder extends RecyclerView.ViewHolder {
        final ItemGroupBinding binding;
        final Context context;

        public GroupListAdapterViewHolder(Context context, ItemGroupBinding binding) {
            super(binding.cardView);
            this.context = context;
            this.binding = binding;
        }

        void bindGroup(Group group) {
            binding.setViewModel(new ItemGroupViewModel(context, group));
        }
    }
}
