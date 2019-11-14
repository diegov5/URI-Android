package com.uri.diegovaldezlocal.uriux.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.uri.diegovaldezlocal.uriux.R;

/**
 * Created by Diego Valdez Local on 6/14/2017.
 */

public class SignUpFragment extends Fragment {
    public SignUpFragment() {
        // needs to be empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the layout for this fragment
        getActivity().setTitle("Sign Up");
        final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_signup, container, false);
        WebView engine = (WebView) mLinearLayout.findViewById(R.id.web_engine);
        engine.getSettings().setJavaScriptEnabled(true);
        engine.loadUrl("https://research.uriux.com/jfe/form/SV_7PsNmaRQBGnmnGZ");
        engine.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                view.loadUrl(url);
                return false;
            }

        }); return mLinearLayout;
    }
}
