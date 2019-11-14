package com.uri.diegovaldezlocal.uriux.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.uri.diegovaldezlocal.uriux.R;

/**
 * Created by Diego Valdez Local on 6/7/2017.
 */

public class AboutFragment extends Fragment {
    public AboutFragment() {
        // needs to be empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the layout for this fragment
        getActivity().setTitle("About Us");
        final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_about, container, false);

        ImageButton instagram = (ImageButton) mLinearLayout.findViewById(R.id.IG);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.instagram.com/uri_ux"));
                startActivity(intent);
            }
        });

        ImageButton facebook = (ImageButton) mLinearLayout.findViewById(R.id.FB);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.facebook.com/UserResearchInternational"));
                startActivity(intent);
            }
        });

        ImageButton twitter = (ImageButton) mLinearLayout.findViewById(R.id.TW);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.twitter.com/URI_UX"));
                startActivity(intent);
            }
        });
        return mLinearLayout;
    }
}
