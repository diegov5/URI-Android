package com.uri.diegovaldezlocal.uriux;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uri.diegovaldezlocal.uriux.fragment.AboutFragment;
import com.uri.diegovaldezlocal.uriux.fragment.ContactFragment;
import com.uri.diegovaldezlocal.uriux.fragment.RecentPostsFragment;
import com.uri.diegovaldezlocal.uriux.fragment.SignUpFragment;

import static android.R.attr.fragment;

/**
 * Created by Diego Valdez Local on 6/26/2017.
 */

public class PostDetailActivity extends AppCompatActivity{

    private static final String TAG = "PostDetailActivity";

    public static final String EXTRA_POST_KEY = "post_key";

    private DatabaseReference mPostReference;
    private String[] drawerItems;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mPostKey;
    private DrawerLayout mDrawerLayout;

    private TextView mStudyName;
    private TextView mAddress;
    private TextView mStart;
    private TextView mEnd;
    private TextView mGratuity;
    private TextView mDuration;
    private Button mSurvey;
    private TextView mblurb;

    protected void onCreate(Bundle savedInstanceState)  {
        //Fragment fragment = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        /*drawerItems = getResources().getStringArray(R.array.nav_item_activity_titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
        }
*/
        setTitle("Study Details");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get post key from intent
        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference(mPostKey);

        // Initialize Views
        mStudyName = (TextView) findViewById(R.id.studyName);
        mAddress = (TextView) findViewById(R.id.address);
        mStart = (TextView) findViewById(R.id.start);
        mEnd = (TextView) findViewById(R.id.end);
        mGratuity = (TextView) findViewById(R.id.gratuity);
        mDuration = (TextView) findViewById(R.id.duration);
        mSurvey = (Button) findViewById(R.id.survey);
        mblurb = (TextView) findViewById(R.id.blurb);
    }
/*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.uniHome) {
            this.finish();
            return true;
        }
        this.finish();
        return super.onOptionsItemSelected(item);
    }
/*
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        if (id == R.id.home) {
            fragment = new RecentPostsFragment();
        }/* else if (id == R.id.categories) {
            // TODO
        } else if (id == R.id.near) {
            // TODO
        } else if (id == R.id.signup) {
            fragment = new SignUpFragment();
        } else if (id == R.id.contact) {
            fragment = new ContactFragment();
        } else if (id == R.id.about) {
            fragment = new AboutFragment();
        } else if (id == R.id.insta) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.instagram.com/uri_ux"));
            startActivity(intent);
        } else if (id == R.id.facebook) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.facebook.com/UserResearchInternational"));
            startActivity(intent);
        } else if (id == R.id.twitter) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.twitter.com/URI_UX"));
            startActivity(intent);
        }


        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/


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
                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mPostReference.addValueEventListener(postListener);
        mPostListener = postListener;
    }
}