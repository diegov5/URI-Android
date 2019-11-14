package com.uri.diegovaldezlocal.uriux.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uri.diegovaldezlocal.uriux.MainActivity;
import com.uri.diegovaldezlocal.uriux.Post;
import com.uri.diegovaldezlocal.uriux.PostDetailActivity;
import com.uri.diegovaldezlocal.uriux.PostViewHolder;
import com.uri.diegovaldezlocal.uriux.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public abstract class PostListFragment extends Fragment {

    private static final String TAG = "PostListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Post, PostViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public PostListFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getActivity().setTitle("Upcoming Studies");
        View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        //mManager.setReverseLayout(true);
        //mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.item_post,
                PostViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final PostViewHolder viewHolder, final Post model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        //Fragment fragment = new RecentPostsFragment();
                        //if (fragment != null){
                            //PostDetailActivityFragment fragment2 = new PostDetailActivityFragment();
                            /*FragmentManager fragmentManager = getFragmentManager();
                            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frame_container, fragment2);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
                            intent.putExtra(MainActivity.EXTRA_POST_KEY, postKey);
                            getActivity().startActivity(intent);
                            */
                            Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                            intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey);
                            startActivity(intent);
                        //}
                    }
                });
                viewHolder.bindToPost(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }


    public abstract Query getQuery(DatabaseReference databaseReference);

}
