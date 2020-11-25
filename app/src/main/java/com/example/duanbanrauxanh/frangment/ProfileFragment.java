package com.example.duanbanrauxanh.frangment;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanbanrauxanh.R;
import com.example.duanbanrauxanh.dao.AccountDAO;

import java.util.List;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private CardView cVNews, cVProfile, cVMap;
    private TextView tvnews, tvprofile, tvMap;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        anhXa(view);
        cVProfile.setOnClickListener(this::onClick);
        cVMap.setOnClickListener(this::onClick);

        return view;
    }


    //phần ánh xạ và áp dụng font chữ
    private void anhXa(View view) {
        cVNews = view.findViewById(R.id.news);
        cVProfile = view.findViewById(R.id.profile);
        tvnews = view.findViewById(R.id.newstv);
        tvprofile = view.findViewById(R.id.profiletv);
        cVMap = view.findViewById(R.id.maptv);
        tvMap = view.findViewById(R.id.maptxt);
        //áp dụng font chữ
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "roboto_boldItalic.ttf");
        tvprofile.setTypeface(typeface);
        tvnews.setTypeface(typeface);
        tvMap.setTypeface(typeface);


    }

    @Override
    public void onClick(View view) {
        if (view == cVMap) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            MapsFragment pf = new MapsFragment();
            fragmentTransaction.replace(R.id.action_hContainer, pf).commit();
            Toast.makeText(getContext(), " Maps ", Toast.LENGTH_SHORT).show();

        }
        if (view == cVProfile) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ProfileHintFragment pf = new ProfileHintFragment();
            fragmentTransaction.replace(R.id.action_hContainer, pf).commit();
            Toast.makeText(getContext(), "Acount Hint", Toast.LENGTH_SHORT).show();
        }
    }
}