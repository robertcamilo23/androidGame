package edu.luc.etl.cs313.android.group3Game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;


import edu.luc.etl.cs313.android.group3Game.R;
import edu.luc.etl.cs313.android.group3Game.model.Dot;
import edu.luc.etl.cs313.android.group3Game.model.Dots;

import static edu.luc.etl.cs313.android.group3Game.model.Constants.*;


/**
 * I see spots!
 *
 * @author <a href="mailto:android@callmeike.net">Blake Meike</a>
 */
public class DotView extends View
{

    private volatile Dots dots;
    Bitmap greenMonster = BitmapFactory.decodeResource( getResources(), R.drawable.hulk );
    Bitmap yellowMonster = BitmapFactory.decodeResource( getResources(), R.drawable.star );


    /**
     * @param context the rest of the application
     */
    public DotView( Context context )
    {
        super( context );
        setFocusableInTouchMode( true );
    }

    /**
     * @param context
     * @param attrs
     */
    public DotView( Context context, AttributeSet attrs )
    {
        super( context, attrs );
        setFocusableInTouchMode( true );
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public DotView( Context context, AttributeSet attrs, int defStyle )
    {
        super( context, attrs, defStyle );
        setFocusableInTouchMode( true );
    }

    /**
     * @param dots
     */
    public void setDots( Dots dots )
    { this.dots = dots; }

    /**
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw( Canvas canvas )
    {
        Paint paint = new Paint();
        paint.setStyle( Style.STROKE );
        paint.setColor( Color.RED );
        canvas.drawRect( 0, 0, getWidth() - 1, getHeight() - 1, paint );

        if ( null == dots )
        {
            return;
        }

        for ( int i = 0 ; i < dots.getWidth() ; i++ )
        {
            for ( int j = 0 ; j < dots.getHeight() ; j++ )
            {
                canvas.drawRect( i * DOT_DIAMETER, j * DOT_DIAMETER, ( i * DOT_DIAMETER ) + DOT_DIAMETER, ( j * DOT_DIAMETER ) + DOT_DIAMETER, paint );
            }
        }

        paint.setStyle( Style.FILL );
        for ( Dot dot : dots.getDots() )
        {
            if ( dot.getColor() == Color.YELLOW )
            {
                canvas.drawBitmap( yellowMonster, dot.getX() - dot.getRadius(), dot.getY() - dot.getRadius(), paint );
            }
            else
            {
                canvas.drawBitmap( greenMonster, dot.getX() - dot.getRadius(), dot.getY() - dot.getRadius(), paint );
            }
        }
    }
}