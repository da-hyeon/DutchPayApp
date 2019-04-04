package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.GroupParticipants;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.databinding.ItemMyGroupBinding;

import java.util.ArrayList;

public class Listview_GroupParticipantsAdapter extends BaseAdapter {

    private ItemMyGroupBinding mBinding;
    private Context mContext;
    private ArrayList<GroupParticipants> mGroupParticipantsArrayList;

    public Listview_GroupParticipantsAdapter(Context mContext, ArrayList<GroupParticipants> mGroupParticipantsArrayList) {
        this.mContext = mContext;
        this.mGroupParticipantsArrayList = mGroupParticipantsArrayList;
    }

    @Override
    public int getCount() {
        return mGroupParticipantsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGroupParticipantsArrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_my_group, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ItemMyGroupBinding) v.getTag();
        }
        mBinding.tvPersonCount.setText(mGroupParticipantsArrayList.get(position).getName());
        mBinding.tvGroupName.setText(mGroupParticipantsArrayList.get(position).getPhoneNumber());
        v.setTag(mBinding);
        return v;
    }
}
