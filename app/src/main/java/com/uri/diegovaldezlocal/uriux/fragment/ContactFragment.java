package com.uri.diegovaldezlocal.uriux.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.uri.diegovaldezlocal.uriux.Address;
import com.uri.diegovaldezlocal.uriux.R;

/**
 * Created by Diego Valdez Local on 6/13/2017.
 */

public class ContactFragment extends Fragment {
    public ContactFragment() {
        // needs to be empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the layout for this fragment
        getActivity().setTitle("Contact Us");
        final String[] TO = {"info@uriux.com"};
        final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_contact, container, false);

        final ImageButton email = (ImageButton) mLinearLayout.findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

        ImageButton call = (ImageButton) mLinearLayout.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4252428030"));
                startActivity(intent);
            }
        });

        ImageButton address = (ImageButton) mLinearLayout.findViewById(R.id.address);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Address.class);
                startActivity(intent);
            }
        });

        final Button emailText = (Button) mLinearLayout.findViewById(R.id.emailText);
        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

        Button callText = (Button) mLinearLayout.findViewById(R.id.phoneText);
        callText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4252428030"));
                startActivity(intent);
            }
        });

        Button addressText = (Button) mLinearLayout.findViewById(R.id.addressText);
        addressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Address.class);
                startActivity(intent);
            }
        });

        return mLinearLayout;
    }

}

