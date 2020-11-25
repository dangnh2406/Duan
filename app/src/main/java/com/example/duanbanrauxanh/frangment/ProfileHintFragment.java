package com.example.duanbanrauxanh.frangment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duanbanrauxanh.R;
import com.example.duanbanrauxanh.adapter.AccountAdapter;
import com.example.duanbanrauxanh.dao.AccountDAO;
import com.example.duanbanrauxanh.model.User;

import java.util.List;


public class ProfileHintFragment extends Fragment {
    private    ListView listView;
    private    AccountDAO accountDAO;
    private    List<User> users;

    public ProfileHintFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_hint, container, false);
        listView = view.findViewById(R.id.listViewProfileHint);
        filtoList();
        return view;
    }

    private void filtoList() {
        accountDAO = new AccountDAO(getContext());
        users = accountDAO.getAllAcount();
        AccountAdapter accountAdapter = new AccountAdapter(getContext(), users);
        listView.setAdapter(accountAdapter);
    }
}