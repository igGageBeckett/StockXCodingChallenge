package gagebeckett.com.stockxcodingchallenge.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import gagebeckett.com.stockxcodingchallenge.R;
import gagebeckett.com.stockxcodingchallenge.data.DataManager;
import gagebeckett.com.stockxcodingchallenge.model.Response;
import gagebeckett.com.stockxcodingchallenge.model.RedditPostList;

import static gagebeckett.com.stockxcodingchallenge.model.Constants.BROADCAST_DATA_IS_READY;
import static gagebeckett.com.stockxcodingchallenge.model.Constants.BROADCAST_POST_SELECTED;
import static gagebeckett.com.stockxcodingchallenge.model.Constants.EXTRA_REDDIT_JSON_DATA;
import static gagebeckett.com.stockxcodingchallenge.model.Constants.EXTRA_REDDIT_POST_URL;


public class RedditPostFragment extends Fragment {

    private List<Response.ResponseChildData> mRedditResponseDataList;
    private DataManager mDataManager;
    private Context mApplicationContext;
    private RedditPostRecyclerViewAdapter mRedditPostAdapter;


    private OnListFragmentInteractionListener mListener;

    public RedditPostFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplicationContext = getActivity().getApplicationContext();

        RedditPostList redditPostList = new RedditPostList();
        if(redditPostList.getResponseData() != null) {
            mRedditResponseDataList = redditPostList.getResponseData().getChildren();
        } else {
            mRedditResponseDataList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reddit_post_list, container, false);
        RecyclerView redditPostRecyclerView = view.findViewById(R.id.redditPostList_RecyclerView);
        mRedditPostAdapter = new RedditPostRecyclerViewAdapter(mRedditResponseDataList);

        redditPostRecyclerView.setAdapter(mRedditPostAdapter);
        redditPostRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public void redditPostDataUpdated() {
        mRedditResponseDataList.clear();
        mRedditResponseDataList.addAll(new RedditPostList().getResponseData().getChildren());
        mRedditPostAdapter.updateList();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener { }

    public class RedditPostRecyclerViewAdapter extends RecyclerView.Adapter<RedditPostRecyclerViewAdapter.ViewHolder> {

        private final List<Response.ResponseChildData> mValues;

        public RedditPostRecyclerViewAdapter(List<Response.ResponseChildData> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_redditpost, parent, false);
            return new ViewHolder(view, mValues.get(viewType));
        }
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(BROADCAST_POST_SELECTED);
                    intent.putExtra(EXTRA_REDDIT_POST_URL, "https://reddit.com" + mValues.get(holder.getAdapterPosition()).getData().getPermalink());
                    LocalBroadcastManager.getInstance(getActivity().getBaseContext()).sendBroadcast(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        public void updateList() {
            mValues.clear();
            mValues.addAll(new RedditPostList().getResponseData().getChildren());
            this.notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public TextView mRedditPostTextView;
            public ImageView mRedditPostImageView;
            public TextView mSubRedditTextView;
            public TextView mRedditUserNameTextView;
            public TextView mRedditUpVoteCount;
            public Response.ChildData mItem;

            public ViewHolder(View view, Response.ResponseChildData RedditPostData) {
                super(view);
                mView = view;
                mItem = RedditPostData.getData();
                mRedditPostTextView = view.findViewById(R.id.RedditPost_TextView);
                mRedditPostImageView = view.findViewById(R.id.redditImage_ImageView);
                mSubRedditTextView = view.findViewById(R.id.subreddit_TextView);
                mRedditUserNameTextView = view.findViewById(R.id.userName_TextView);
                mRedditUpVoteCount = view.findViewById(R.id.upVoteCount_TextView);
                Glide.with(view)
                        .load(mItem.getThumbnail())
                        .into(mRedditPostImageView);
                mRedditPostTextView.setText(mItem.getTitle());
                mSubRedditTextView.setText(mItem.getSubreddit_name_prefixed());
                mRedditUserNameTextView.setText(mItem.getAuthor());
                mRedditUpVoteCount.setText(mItem.getUps());

            }
        }
    }
}
