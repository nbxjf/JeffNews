package com.declan.jeffnews.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.declan.jeffnews.R;
import com.declan.jeffnews.news.NewsAdapter;

/**
 * Created by Jeff_xu on 2016/9/16.
 */
public class NewsListFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mType;
    private RecyclerView mRecyclerView;

    public static NewsListFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, type);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist,container,false);
//        TextView textView = (TextView) view.findViewById(R.id.textView);
//        textView.setText("第"+mType+"页");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setAdapter(new NewsAdapter());
        return view;
    }

}
