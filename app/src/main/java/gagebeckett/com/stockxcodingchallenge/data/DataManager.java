package gagebeckett.com.stockxcodingchallenge.data;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gagebeckett.com.stockxcodingchallenge.model.RedditPostList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static gagebeckett.com.stockxcodingchallenge.model.Constants.BROADCAST_DATA_IS_READY;
import static gagebeckett.com.stockxcodingchallenge.model.Constants.EXTRA_REDDIT_JSON_DATA;

public class DataManager {
    private final String BASE_URL = "https://www.reddit.com";
    private final String APPEND_JSON = "/.json";
    private OkHttpClient mHttpClient = new OkHttpClient();
    private String mSubReddit;
    private String mQueryString;
    private String mDataResponse;
    private static Context mContext;
    private static DataManager dataManagerInstance = null;

    private DataManager() {
        initializeData();
        mSubReddit = "";
    }

    public static DataManager getDataManagerInstance(Context context) {
        if (dataManagerInstance == null) {
            dataManagerInstance = new DataManager();
            mContext = context;
        }
        return dataManagerInstance;
    }

    public void setSubReddit(String subReddit) {
        mSubReddit = (subReddit.length() > 0) ? "/r/" + subReddit : "";
        initializeData();
    }

    private void initializeData() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                mQueryString = BASE_URL + mSubReddit + APPEND_JSON;
                Log.d("URL*************", mQueryString);
                Request request = new Request.Builder()
                        .url(mQueryString)
                        .build();
                Response response;
                try {
                    response = mHttpClient.newCall(request).execute();
                    mDataResponse = response.body().string();
                    dataIsReady();

                } catch (IOException e) {
                    e.printStackTrace();
                    mDataResponse = null;
                }
            }
        });
    }

    public String getDataResponse() {
        return mDataResponse;
    }

    private void dataIsReady() {
        RedditPostList redditPostList = new RedditPostList(mDataResponse);
        redditPostList.createPostList();
        Intent intent = new Intent();
        intent.setAction(BROADCAST_DATA_IS_READY);
        intent.putExtra(EXTRA_REDDIT_JSON_DATA, mDataResponse);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }
}


