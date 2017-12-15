package com.example.ll.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ll.mvp.api.DoubanManager;
import com.example.ll.mvp.api.IDoubanService;
import com.example.ll.mvp.books.BookPresenter;
import com.example.ll.mvp.books.BooksFragment;
import com.example.ll.mvp.movies.MovieFragment;
import com.example.ll.mvp.movies.MoviePresenter;
import com.example.ll.mvp.util.DialogUtils;
import com.example.ll.mvp.wxapi.UserVoinfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/11/30 0030.
 */


public class HomeFragment extends Fragment {
    public ContextApplication myApplication;
    @BindView(R.id.sliding_tab)
    TabLayout mSlidingTab;
    @BindView(R.id.main_vp)
    ViewPager mMainVp;
    Unbinder unbinder;
    private UserVoinfo userVoinfo;
    private String userid;
    private IDoubanService mIDoubanService;
//    @BindView(R.id.sliding_tab)
//    TabLayout mSlidingTab;
//    @BindView(R.id.main_vp)
//    ViewPager mMainVp;

    MainViewPagerAdapter mViewPageradapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupViewPager(mMainVp);
        if (mSlidingTab != null) {
            mSlidingTab.addTab(mSlidingTab.newTab());
            mSlidingTab.addTab(mSlidingTab.newTab());
            mSlidingTab.setupWithViewPager(mMainVp);
        }
        Log.i("TAGE","HomeFragment: onCreateView");
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bmob.initialize(this,"d1e842d735dd798b7d14435d1f0bb340");
//        setContentView(R.layout.activity_home);
//        ButterKnife.bind(this);
//        myApplication = (ContextApplication) getApplicationContext();
//        userVoinfo = myApplication.getUserVoinfo();
//        if (myApplication.getUserVoinfo() != null) {
//            userid = userVoinfo.getUserid();
//        }
//        if(TextUtils.isEmpty(userid)){
//            Intent intent=new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            this.finish();
//        }else{
//            userid=userVoinfo.getUserid();
//        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                showShare();
            }
        });
//        setupViewPager(mMainVp);
//        if (mSlidingTab != null) {
//            mSlidingTab.addTab(mSlidingTab.newTab());
//            mSlidingTab.addTab(mSlidingTab.newTab());
//            mSlidingTab.setupWithViewPager(mMainVp);
//        }
    }

    private void setupViewPager(final ViewPager viewPager) {
        mViewPageradapter = new MainViewPagerAdapter(getChildFragmentManager());
        MovieFragment movieFragment = MovieFragment.newInstance();
        BooksFragment booksFragment = BooksFragment.newInstance();
        mViewPageradapter.addFragment(movieFragment, "电影");
        mViewPageradapter.addFragment(booksFragment, "书籍");
        viewPager.setAdapter(mViewPageradapter);
        creatMoviePresenter(movieFragment, booksFragment);
    }

    private void creatMoviePresenter(MovieFragment fragment, BooksFragment fragment2) {
        mIDoubanService = DoubanManager.creatDoubanService();
        new MoviePresenter(mIDoubanService, fragment);
        new BookPresenter(mIDoubanService, fragment2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MainViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mTitles = new ArrayList<>();

        public MainViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(getContext());
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            DialogUtils.showDialog(this, myApplication);
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}

