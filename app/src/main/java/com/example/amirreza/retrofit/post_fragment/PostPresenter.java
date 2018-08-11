package com.example.amirreza.retrofit.post_fragment;

import com.example.amirreza.retrofit.data.model.Post;
import com.example.amirreza.retrofit.data.PostRepository;

import java.util.List;

public class PostPresenter implements PostPresenterInterface.View,
        PostPresenterInterface.PostActionListerner {

    private PostPresenterInterface.View postsViewListener;
    private PostRepository postsRepository;

    public PostPresenter(PostPresenterInterface.View postsViewListener,
                         PostRepository postsRepository) {
        this.postsViewListener = postsViewListener;
        this.postsRepository = postsRepository;
    }

    @Override
    public void onLoadPost(int id) {
//        PostRepository.getPostsData(id, new GetPosts());
        postsRepository.getPostsData(id, new GetPosts());

    }

    @Override
    public void showPostsList(List<Post> posts) {

    }

    private class GetPosts implements GetPostsInterface {

        @Override
        public void loadPostData(List<Post> posts) {
            postsViewListener.showPostsList(posts);
        }
    }

    public interface GetPostsInterface{


        void loadPostData(List<Post> posts);
    }
}