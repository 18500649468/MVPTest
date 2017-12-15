package com.example.ll.mvp.books;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public abstract class BaseIbooks  implements Ibooks {
    private Context mcontext;
    public BaseIbooks(Context context) {
        mcontext=context;
    }

    @Override
    public void read() {
        Toast.makeText(mcontext,"read",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void look() {
        Toast.makeText(mcontext,"look",Toast.LENGTH_SHORT).show();
    }
    public abstract void listen();
}
