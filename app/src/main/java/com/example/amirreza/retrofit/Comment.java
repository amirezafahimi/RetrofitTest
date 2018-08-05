package com.example.amirreza.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

        @SerializedName("postId")
        @Expose
        private Integer postId;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("body")
        @Expose
        private String body;

        public Integer getPostId() {
            return postId;
        }

        public void setPostId(Integer postId) {
            this.postId = postId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

}
