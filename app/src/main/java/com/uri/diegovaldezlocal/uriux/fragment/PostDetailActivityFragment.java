package com.uri.diegovaldezlocal.uriux.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uri.diegovaldezlocal.uriux.MainActivity;
import com.uri.diegovaldezlocal.uriux.Post;
import com.uri.diegovaldezlocal.uriux.PostDetailActivity;
import com.uri.diegovaldezlocal.uriux.R;

/**
 * Created by Diego Valdez Local on 7/10/2017.
 */

public class PostDetailActivityFragment extends Fragment {
    public PostDetailActivityFragment() {
        // needs to be empty
    }

    private static final String TAG = "PostDetailActivity";

    public static final String EXTRA_POST_KEY = "post_key";

    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mPostKey;

    private TextView mStudyName;
    private TextView mAddress;
    private TextView mStart;
    private TextView mEnd;
    private TextView mGratuity;
    private TextView mDuration;
    private Button mSurvey;
    private TextView mblurb;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final LinearLayout Layout = (LinearLayout) inflater.inflate(R.layout.activity_post_detail, container, false);
        getActivity().setTitle("Study Details");

        // Get post key from intent
        mPostKey = MainActivity.postKey;
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference(mPostKey);

        // Initialize Views
        mStudyName = (TextView) Layout.findViewById(R.id.studyName);
        mAddress = (TextView) Layout.findViewById(R.id.address);
        mStart = (TextView) Layout.findViewById(R.id.start);
        mEnd = (TextView) Layout.findViewById(R.id.end);
        mGratuity = (TextView) Layout.findViewById(R.id.gratuity);
        mDuration = (TextView) Layout.findViewById(R.id.duration);
        mSurvey = (Button) Layout.findViewById(R.id.survey);
        mblurb = (TextView) Layout.findViewById(R.id.blurb);

        return Layout;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                final Post post = dataSnapshot.getValue(Post.class);
                // [START_EXCLUDE]
                if (post != null) {
                    mStudyName.setText(post.name);
                    mAddress.setText(post.address);
                    mStart.setText(post.start);
                    if (post.end != null)
                        mEnd.setText("- " + post.end);
                    mGratuity.setText(post.gratuity);
                    mDuration.setText(post.duration);
                    mblurb.setText(post.blurb);
                    mSurvey.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            if (!post.survey.startsWith("http://") && !post.survey.startsWith("https://")) {
                                intent.setData(Uri.parse("https://" + post.survey));
                            }
                            else {
                                intent.setData(Uri.parse(post.survey));
                            }
                            startActivity(intent);
                        }
                    });
                }

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        };
        mPostReference.addValueEventListener(postListener);
        mPostListener = postListener;
    }
}