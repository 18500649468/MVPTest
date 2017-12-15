package com.example.ll.mvp.books;

import com.example.ll.mvp.BasePresenter;
import com.example.ll.mvp.BaseView;
import com.example.ll.mvp.beans.BookInfo;

import java.util.List;

/**
 * Created by LL on 2017/7/6.
 */

public interface BooksContract {
    interface View extends BaseView<Presenter> {
        void showBooks(List<BookInfo.StoriesBean> books);
        void showNoBooks();
        void showBookDetailUi(String bookName);
        void setLoadingIndicator(boolean active);
    }
    interface Presenter extends BasePresenter{
        void loadBooks(boolean forceUpdate);

    }
}
