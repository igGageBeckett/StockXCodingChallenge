package gagebeckett.com.stockxcodingchallenge.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static gagebeckett.com.stockxcodingchallenge.model.Response.*;

public class RedditPostList {
    private Gson mGson;
    private String mJsonPostData;
    private static Response.ResponseData mRedditJsonResponse;
    Response mRedditPostList;

    public RedditPostList() {

    }

    public RedditPostList(String jsonPostData) {
        mJsonPostData = jsonPostData;
        mGson = new GsonBuilder().serializeNulls().create();
    }

    public void createPostList () {

        mRedditPostList = mGson.fromJson(mJsonPostData, Response.class);
        mRedditJsonResponse = mRedditPostList.getData();
    }
    public ResponseData getResponseData() {
        return mRedditJsonResponse;
    }

}
