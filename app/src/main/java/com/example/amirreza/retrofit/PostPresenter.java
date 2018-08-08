package com.example.amirreza.retrofit;

import com.example.amirreza.retrofit.data.model.Posts;

import java.util.List;

public class PostPresenter implements PostPresenterInterface.View,
        PostPresenterInterface.PostActionListerner {

    private PostPresenterInterface.View postsViewListener;
    private PostRepository postsRepository;

    public PostPresenter(PostPresenterInterface.View postsViewListener, PostRepository postsRepository) {
        this.postsViewListener = postsViewListener;
        this.postsRepository = postsRepository;
    }

    @Override
    public void getPostsData(int id) {
//        PostRepository.getPostsData(id, new GetPosts());
        postsRepository.getPostsData(id, new GetPosts());

    }

    @Override
    public void showPostsList(List<Posts> posts) {

    }

    private class GetPosts implements GetPostsInterface {

        @Override
        public void loadPostData(List<Posts> posts) {
            postsViewListener.showPostsList(posts);
        }
    }

    interface GetPostsInterface{


        void loadPostData(List<Posts> posts);
    }
}
