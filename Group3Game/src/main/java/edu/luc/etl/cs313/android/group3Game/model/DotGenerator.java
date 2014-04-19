package edu.luc.etl.cs313.android.group3Game.model;

import android.os.Handler;

import edu.luc.etl.cs313.android.group3Game.view.DotView;

/**
 * Created by Hamdan on 4/18/14.
 */
public class DotGenerator implements Runnable
{
    final Dots dots;
    final DotView view;
    final int color;

    private final Handler hdlr = new Handler();
    private final Runnable makeDots = new Runnable()
    {

        @Override
        public void run() { dots.addDot1(); }
    };


    private volatile boolean done;

    public DotGenerator( Dots dots, DotView view, int color )
    {
        this.dots = dots;
        this.view = view;
        this.color = color;
    }

    public void done() { done = true;}

    @Override
    public void run()
    {
        while ( !done )
        {
            hdlr.post( makeDots );
            try
            {
                Thread.sleep( 1000 );
            }
            catch ( InterruptedException e )
            {
            }
        }
    }

}
