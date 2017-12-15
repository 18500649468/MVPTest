package com.example.ll.mvp.books;

import android.content.Context;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class Mybok extends BaseIbooks implements Ibooks{
    public Mybok(Context context) {
        super(context);
    }

    public void start() {
        this.look();
        this.read();
    }

    @Override
    public void listen() {
        start();
    }
}
