package com.example.ll.mvp.books;

import android.util.Log;

import com.example.ll.mvp.api.DoubanManager;
import com.example.ll.mvp.api.IDoubanService;
import com.example.ll.mvp.beans.BookInfo;
import com.example.ll.mvp.beans.Movie;
import com.example.ll.mvp.books.BooksContract.Presenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class BookPresenter implements BooksContract.Presenter {
    private IDoubanService mIDoubanService;
    private List<BookInfo.StoriesBean> storiesBeanlist;
    private BooksContract.View booksFragment;

    public BookPresenter() {
    }

    public BookPresenter(IDoubanService iDoubanService, BooksContract.View booksFragment) {
        this.mIDoubanService=iDoubanService;
        this.booksFragment = booksFragment;
        booksFragment.setPresenter(this);
    }

    @Override
    public void start() {
        loadBooks(true);
    }

    @Override
    public void loadBooks(boolean load) {
        mIDoubanService.getLatestNews().enqueue(new Callback<BookInfo>() {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                storiesBeanlist=response.body().getStories();
                Log.i("storiesBeanlist",storiesBeanlist.toString()+"");
                if (storiesBeanlist.isEmpty()){
                    processEmptyTasks();
                }else{
                    booksFragment.setLoadingIndicator(false);
                    booksFragment.showBooks(storiesBeanlist);
                }
            }

            @Override
            public void onFailure(Call<BookInfo> call, Throwable t) {
                booksFragment.setLoadingIndicator(false);
                processEmptyTasks();
            }
        });
    }


    private void processEmptyTasks(){
        //MoviesFragment 需要提示没有数据
        booksFragment.showNoBooks();
    }
}
