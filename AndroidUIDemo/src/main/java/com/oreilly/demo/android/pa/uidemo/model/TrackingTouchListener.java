package com.oreilly.demo.android.pa.uidemo.model;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.oreilly.demo.android.pa.uidemo.R;

/**
 * Created by Hamdan on 4/18/14.
 */
     public class TrackingTouchListener implements View.OnTouchListener
{
    private final Dots mDots;
    private int count;
    private TextView tvs;
    public TrackingTouchListener(Dots dots){
        mDots =dots;
        count = 0;


   }
    @Override public boolean onTouch(View v, MotionEvent evt) {

        int k = mDots.checkCellId(evt.getY(), evt.getX());

        if (mDots.getCells().get(k).getDot() != null)
            if (mDots.getCells().get(k).getDot().getState() == 1) {
                mDots.getCells().get(k).remove();
                count++;
                tvs.setText(Integer.toString(count));

            }
        return true;
    }
      public void setTvs(TextView)
    {
        this.tvs = tvs;
    }
    }