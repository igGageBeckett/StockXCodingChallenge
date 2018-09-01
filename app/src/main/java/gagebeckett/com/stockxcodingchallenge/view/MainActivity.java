package gagebeckett.com.stockxcodingchallenge.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gagebeckett.com.stockxcodingchallenge.R;
import gagebeckett.com.stockxcodingchallenge.data.DataManager;

import static gagebeckett.com.stockxcodingchallenge.model.Constants.BROADCAST_DATA_IS_READY;
import static gagebeckett.com.stockxcodingchallenge.model.Constants.BROADCAST_POST_SELECTED;
import static gagebeckett.com.stockxcodingchallenge.model.Constants.EXTRA_REDDIT_POST_URL;

public class MainActivity extends AppCompatActivity implements RedditPostFragment.OnListFragmentInteractionListener {
    EditText mSubRedditTextEdit;
    Button mSubRedditSubmitButton;
    Fragment mCurrentFragment;
    DataManager mDataManager;
    WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataManager = DataManager.getDataManagerInstance(this);
        IntentFilter intentDataUpdated = new IntentFilter(BROADCAST_DATA_IS_READY);
        LocalBroadcastManager.getInstance(this).registerReceiver(mDataUpdated, intentDataUpdated);

        IntentFilter intentPostSelected = new IntentFilter(BROADCAST_POST_SELECTED);
        LocalBroadcastManager.getInstance(this).registerReceiver(mPostSelected, intentPostSelected);
        mSubRedditTextEdit = findViewById(R.id.search_edit);
        mSubRedditSubmitButton = findViewById(R.id.search_submit);
        mWebView = findViewById(R.id.webview);

        mSubRedditTextEdit.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mSubRedditSubmitButton.performClick();

                    return true;
                }
                return false;
            }
        });

        mSubRedditSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataManager.setSubReddit(mSubRedditTextEdit.getText().toString());

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        mWebView.setVisibility(View.GONE);
        mSubRedditTextEdit.setVisibility(View.VISIBLE);
    }

    private BroadcastReceiver mDataUpdated = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RedditPostFragment redditPostFragment = new RedditPostFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_Layout, redditPostFragment);
            fragmentTransaction.commit();
            mCurrentFragment = redditPostFragment;

        }
    };

    private BroadcastReceiver mPostSelected = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String url = intent.getStringExtra(EXTRA_REDDIT_POST_URL);
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.loadUrl(url);
            mWebView.setVisibility(View.VISIBLE);
            mSubRedditTextEdit.setVisibility(View.GONE);
        }
    };
}
