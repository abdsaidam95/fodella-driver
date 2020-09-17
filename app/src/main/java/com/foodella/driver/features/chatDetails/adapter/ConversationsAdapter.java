package com.foodella.driver.features.chatDetails.adapter;

import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.R;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.ItemChatLeftBinding;
import com.foodella.driver.databinding.ItemChatRightBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class ConversationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int MESSAGE_LEFT = 0;
    public static final int MESSAGE_RIGHT = 1;
    public ArrayList<Conversation> conversations = new ArrayList<>();
    public int viewCount;


    public ConversationsAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MESSAGE_RIGHT) {
            ItemChatRightBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_chat_right, parent, false);
            return new RightViewHolder(binding);
        } else {
            ItemChatLeftBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_chat_left, parent, false);
            return new LeftViewHolder(binding);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RightViewHolder) {
            ((RightViewHolder) holder).bind(conversations.get(position));

        } else if (holder instanceof LeftViewHolder) {
            ((LeftViewHolder) holder).bind(conversations.get(position));

        }


    }

    @Override
    public int getItemCount() {
        Log.d("ABR", "MESSAGES: " + conversations.size());
        return conversations.size();
    }

    public void setData(ArrayList<Conversation> messages) {
        messages.clear();
        conversations.addAll(messages);
        notifyDataSetChanged();
    }

    public void appendData(ArrayList<Conversation> messages) {
        conversations.addAll(messages);
        notifyDataSetChanged();
    }

    class RightViewHolder extends RecyclerView.ViewHolder {

        ItemChatRightBinding itemChatRightBinding;

        public RightViewHolder(ItemChatRightBinding itemChatRightBinding) {
            super(itemChatRightBinding.getRoot());
            this.itemChatRightBinding = itemChatRightBinding;
            itemChatRightBinding.executePendingBindings();
        }

        public void bind(Conversation conversation) {
            ConversationItemViewModel vm = new ConversationItemViewModel(conversation);
            vm.onStart();
            itemChatRightBinding.setViewModel(vm);
            itemChatRightBinding.executePendingBindings();
        }
    }

    class LeftViewHolder extends RecyclerView.ViewHolder {

        ItemChatLeftBinding itemChatLeftBinding;

        public LeftViewHolder(ItemChatLeftBinding itemChatLeftBinding) {
            super(itemChatLeftBinding.getRoot());
            this.itemChatLeftBinding = itemChatLeftBinding;
            itemChatLeftBinding.executePendingBindings();
        }

        public void bind(Conversation conversation) {
            ConversationItemViewModel vm = new ConversationItemViewModel(conversation);
            vm.onStart();
            itemChatLeftBinding.setViewModel(vm);
            itemChatLeftBinding.executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        try {
            if (position == 0) {
                return viewCount = MESSAGE_LEFT;
            } else {
                return viewCount = MESSAGE_RIGHT;
            }

        } catch (Exception e) {

        }
        return viewCount;
    }
}
