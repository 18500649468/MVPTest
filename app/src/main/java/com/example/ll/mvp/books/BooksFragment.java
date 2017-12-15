package com.example.ll.mvp.books;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ll.mvp.R;
import com.example.ll.mvp.api.DoubanManager;
import com.example.ll.mvp.beans.BookInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class BooksFragment extends Fragment implements BooksContract.View {
    private BookPresenter bookPresenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<BookInfo.StoriesBean> list = new ArrayList<>();
    private BookAdapter bookAdapter;

    public BooksFragment() {
    }

    public static BooksFragment newInstance(){
        return new BooksFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(bookPresenter!=null){
            bookPresenter.start();
        }else {
            bookPresenter= new BookPresenter(DoubanManager.creatDoubanService(),BooksFragment.this);
            bookPresenter.start();
        }
    }

    @Override
    public void setPresenter(BooksContract.Presenter presenter) {
        bookPresenter= (BookPresenter) presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_books,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.rcv_book);
        progressBar= (ProgressBar) view.findViewById(R.id.bk_loading);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void showBooks(List<BookInfo.StoriesBean> books) {
//        bookAdapter.setData(books);
        if (recyclerView!=null){
            recyclerView.setHasFixedSize(true);
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),1);
            recyclerView.setLayoutManager(layoutManager);
        bookAdapter=new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);
        }
        Log.i("books.size",books.size()+"");
//        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoBooks() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showBookDetailUi(String bookName) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView()==null)return;
        final ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.bk_loading);
        if (active){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }

    }
    class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
        private List<BookInfo.StoriesBean> booklist;

        public BookAdapter(List<BookInfo.StoriesBean> mbooklist) {
            this.booklist = mbooklist;
        }
//        public void setData(List<BookInfo.StoriesBean> data){
//            this.booklist = data;
//            notifyDataSetChanged();
//        }
        @Override
        public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.booklistlayout,parent,false);
            BookViewHolder viewHolder=new BookViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(BookViewHolder holder, int position) {
            if (holder==null)return;
            holder.updataBook(booklist.get(position));
            Log.i("books.size","222");
        }

        @Override
        public int getItemCount() {
            return booklist.size();
        }
        class BookViewHolder extends RecyclerView.ViewHolder{
             BookInfo.StoriesBean bean;
             TextView titleid,titile;
             ImageView imageView;
            public BookViewHolder(View itemView) {
                super(itemView);
                titile= (TextView) itemView.findViewById(R.id.booltitle);
                titleid= (TextView) itemView.findViewById(R.id.bookid);
                imageView= (ImageView) itemView.findViewById(R.id.ivBook);
            }

            public void updataBook(BookInfo.StoriesBean bookbean){
                if (bookbean==null) return;
                this.bean = bookbean;
                Context context = itemView.getContext();
                Picasso.with(context)
                        .load(bookbean.getImages().get(0))
                        .placeholder(context.getResources().getDrawable(R.mipmap.ic_launcher))
                        .into(imageView);
                titile.setText(bookbean.getTitle());
                Log.i("books.size","111");
                titleid.setText(bookbean.getId()+"");
            }
        }
    }

}
