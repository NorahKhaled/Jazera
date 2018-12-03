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

import com.example.nouraalrossiny.androidbottomnav.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button mapBtn;
    public HomeFragment() {
        // Required empty public constructor
    }
    //Two buttons
    private String[] imageUrls = new String[]{
            "https://i.postimg.cc/SRgtWLM8/tamr3.jpg",
            "https://i.postimg.cc/BvfjpSmM/1531505623491.jpg",
            "https://i.postimg.cc/ZK0wGdN7/1440-03-22-14-32-39.jpg",
            "https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView  =inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager viewPager = RootView.findViewById(R.id.view_pager);
        Home_ViewPagerAdapter adapter = new Home_ViewPagerAdapter(getContext(), imageUrls);
        viewPager.setAdapter(adapter);
        setHasOptionsMenu(true);//Make sure you have this line of code.
        mapBtn=RootView.findViewById(R.id.btnMap);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?"+"saddr=9.938083,-84.054430&"+"daddr=9.926392,-84.055964"));
                startActivity(i);
            }
        });


        return RootView;
    }

}