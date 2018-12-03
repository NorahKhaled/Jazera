package com.example.nouraalrossiny.androidbottomnav.Home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.nouraalrossiny.androidbottomnav.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    android.support.v7.widget.Toolbar toolbar;
    Button mapBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    //Two buttons
    private String[] imageUrls = new String[]{

            "https://i.postimg.cc/k4DJBDcJ/coff.jpg",
            "https://i.postimg.cc/SRgtWLM8/tamr3.jpg",
            "https://i.postimg.cc/BvfjpSmM/1531505623491.jpg",
            "https://i.postimg.cc/ZK0wGdN7/1440-03-22-14-32-39.jpg",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_home, container, false);
        toolbar = RootView.findViewById(R.id.toolBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //make it default actionBar for this activity(display name on it)
        ViewPager viewPager = RootView.findViewById(R.id.view_pager);
        Home_ViewPagerAdapter adapter = new Home_ViewPagerAdapter(getContext(), imageUrls);
        viewPager.setAdapter(adapter);
        setHasOptionsMenu(true);//Make sure you have this line of code.
        mapBtn = RootView.findViewById(R.id.btnMap);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/PGPogDo7Eho"));
                startActivity(l);
            }
        });


        return RootView;
    }
}

