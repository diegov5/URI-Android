package com.uri.diegovaldezlocal.uriux;

import com.google.firebase.database.IgnoreExtraProperties;

// [START post_class]
    @IgnoreExtraProperties
    public class Post {

        public String address;
        public String duration;
        public String end;
        public String gratuity;
        public String img;
        public String name;
        public String start;
        public String survey;
        public String blurb;

        public Post() {
            // Default constructor required for calls to DataSnapshot.getValue(Post.class)
        }

        public Post(String address, String duration, String end, String gratuity, String img, String name, String start, String survey, String blurb){
            this.address = address;
            this.duration = duration;
            this.end = end;
            this.gratuity = gratuity;
            this.img = img;
            this.name = name;
            this.start = start;
            this.survey = survey;
            this.blurb = blurb;
        }
    }
// [END post_class]

