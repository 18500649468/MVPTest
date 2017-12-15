package com.example.ll.mvp.movies;

import android.util.Log;

import com.example.ll.mvp.api.IDoubanService;
import com.example.ll.mvp.api.RxSchedulersHelper;
import com.example.ll.mvp.beans.HotMoviesInfo;
import com.example.ll.mvp.beans.Movie;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by LL on 2017/7/6.
 */

public class MoviePresenter implements MoviesContract.Presenter {
    private  MovieFragment mMovieView;
    private  IDoubanService mIDoubanService;
    private boolean mFirstLoad = true;

    public MoviePresenter(IDoubanService mMovieService,MovieFragment movieFragment){
        mMovieView = movieFragment;
        mIDoubanService = mMovieService;
        movieFragment.setPresenter(this);
    }
    @Override
    public void start() {
        loadMovies(false);
    }

    @Override
    public void loadMovies(boolean forceUpdate) {
        loadMovies(forceUpdate||mFirstLoad,true);
        mFirstLoad = false;
    }

    @Override
    public void pullrefresh() {
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("111");
            }
        });
        Observable.just("1").delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mMovieView.stopRefresh();
                    }
                });
    }

    public void loadMovies(boolean forceUpdate,final boolean showLoadingUI){
        if (showLoadingUI){
            mMovieView.setLoadingIndicator(true);
        }
        if (forceUpdate){
            mIDoubanService.searchHotMovies2()
//            mIDoubanService.getTopMovies()
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
                    .compose(RxSchedulersHelper.<HotMoviesInfo>io_main())
                    .subscribe(new Subscriber<HotMoviesInfo>() {
                        @Override
                        public void onCompleted() {
                            if (showLoadingUI)
                                mMovieView.setLoadingIndicator(false);
                        }
                        @Override
                        public void onError(Throwable e) {
                            if (showLoadingUI)
                                mMovieView.setLoadingIndicator(false);
                            processEmptyTasks();
                        }
                        @Override
                        public void onNext(HotMoviesInfo hotMoviesInfo) {
                            List<Movie> movieList =hotMoviesInfo.getMovies();
                            if (showLoadingUI)
                                mMovieView.setLoadingIndicator(false);
                            processMovies(movieList);

                        }
                    });
//            mIDoubanService.searchHotMovies().enqueue(new Callback<HotMoviesInfo>() {
//                @Override
//                public void onResponse(Call<HotMoviesInfo> call, Response<HotMoviesInfo> response) {
//                    List<Movie> movieList = response.body().getMovies();
//                    //获取数据成功
//                    if (showLoadingUI)
//                        mMovieView.setLoadingIndicator(false);
//                    processMovies(movieList);
//                }
////
//                @Override
//                public void onFailure(Call<HotMoviesInfo> call, Throwable t) {
//                    Log.d("tag", "ThreadID + " + Thread.currentThread().getId() + "erro:" + t.getMessage());
//                    if (showLoadingUI)
//                        mMovieView.setLoadingIndicator(false);
//                    processEmptyTasks();
//                }
//            });
        }
    }
    private void processMovies(List<Movie> movies){
        if (movies.isEmpty()){
            processEmptyTasks();
        }else{
            mMovieView.showMovies(movies);
        }
    }
    private void processEmptyTasks(){
        //MoviesFragment 需要提示没有数据
        mMovieView.showNoMovies();
    }

}
