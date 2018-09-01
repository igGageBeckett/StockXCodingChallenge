package gagebeckett.com.stockxcodingchallenge.model;

import com.google.gson.Gson;

import static gagebeckett.com.stockxcodingchallenge.model.Response.*;

public class RedditPostList {
    private Gson mGson;
    private String mJsonPostData;
    private static Response.ResponseData mRedditJsonResponse;

    public RedditPostList() {

    }

    public RedditPostList(String jsonPostData) {
        mJsonPostData = jsonPostData;
        mGson = new Gson();
    }

    public void createPostList () {
        Response redditPostList = mGson.fromJson(mJsonPostData, Response.class);
        mRedditJsonResponse = redditPostList.getData();
    }
    public ResponseData getResponseData() {
        return mRedditJsonResponse;
    }

}
