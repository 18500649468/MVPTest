package com.example.ll.mvp.api;

import com.example.ll.mvp.beans.BookInfo;
import com.example.ll.mvp.beans.HotMoviesInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by LL on 2017/7/5.
 */

public interface IDoubanService {
    String  BASE_URL = "https://api.douban.com/v2/";
    @GET("movie/in_theaters")
//    Call<HotMoviesInfo> searchHotMovies();
    Observable<HotMoviesInfo> searchHotMovies2();
    @GET("http://news-at.zhihu.com/api/4/news/latest")
    Call<BookInfo> getLatestNews();
    @GET("HotMoviesInfo/top250")
    Observable<HotMoviesInfo> getTopMovies();
}
