package edu.luc.etl.cs313.android.group3Game;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import edu.luc.etl.cs313.android.group3Game.model.Dot;
import edu.luc.etl.cs313.android.group3Game.model.Dots;
import edu.luc.etl.cs313.android.group3Game.view.DotView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Android UI demo program
 */
public class TouchMe extends Activity
{
    /**
     * Dot diameter
     * http://mobiforge.com/design-development/designing-touch-thumb-and-finger-sized-design
     */
    public static final int DOT_RADIUS = 44;
    public static final int DOT_DIAMETER = DOT_RADIUS * 2;

    public static int MAX_MONSTERS = 3;
    public static int MONSTERS = 0;
    public static int LEVEL = 1;

    /**
     * The application model
     */
    final Dots dotModel = new Dots();
    private final Random rand = new Random();
    private Thread gameThread;

    /**
     * The application view
     */
    static DotView dotView;
    /**
     * The dot generator
     */
    DotGenerator dotGenerator;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle state )
    {
        super.onCreate( state );


        // install the view
        setContentView( R.layout.main );

        // find the dots view
        dotView = ( DotView ) findViewById( R.id.dots );

        dotView.setDots( dotModel );

        dotView.setOnCreateContextMenuListener( this );
        dotView.setOnTouchListener( new TrackingTouchListener( dotModel, dotGenerator ) );

        dotView.setOnFocusChangeListener( new OnFocusChangeListener()
        {
            @Override
            public void onFocusChange( View v, boolean hasFocus )
            {
                if ( !hasFocus && ( null != dotGenerator ) )
                {
                    dotGenerator.monstersDone();
                    dotGenerator = null;
                }
                else if ( hasFocus && ( null == dotGenerator ) )
                {
                    dotGenerator = new DotGenerator( dotModel, dotView );
                    gameThread = new Thread( dotGenerator );
                    gameThread.start();
                }
            }
        } );

        // wire up the controller
        //        ( ( Button ) findViewById( R.id.button1 ) ).setOnClickListener( new Button.OnClickListener()
        //                                                                        {
        //                                                                            @Override
        //                                                                            public void onClick( View v )
        //                                                                            {
        //                                                                                makeDot( dotModel, dotView );
        //                                                                            }
        //                                                                        }
        //                                                                      );
        //        ( ( Button ) findViewById( R.id.button2 ) ).setOnClickListener( new Button.OnClickListener()
        //                                                                        {
        //                                                                            @Override
        //                                                                            public void onClick( View v )
        //                                                                            {
        //                                                                                makeDot( dotModel, dotView );
        //                                                                            }
        //                                                                        }
        //                                                                      );

        final EditText tb1 = ( EditText ) findViewById( R.id.text1 );
        final EditText tb2 = ( EditText ) findViewById( R.id.text2 );
        dotModel.setDotsChangeListener( new Dots.DotsChangeListener()
        {
            @Override
            public void onDotsChange( Dots dots )
            {
                Dot d = dots.getLastDot();
                // This code makes the UI unacceptably unresponsive.
                // ... investigating - in March, 2014, this was not a problem
                tb1.setText( "LEVEL: " + LEVEL ); // uncommented
                tb2.setText( "MONSTERS: " + MONSTERS ); // uncommented
                dotView.invalidate();
            }
        } );

    }

    /**
     * Install an options menu.
     */
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.simple_menu, menu );
        return true;
    }

    /**
     * Respond to an options menu selection.
     */
    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch ( item.getItemId() )
        {
            case R.id.menu_clear:
                dotModel.clearDots();
                return true;

            default:
                return super.onOptionsItemSelected( item );
        }
    }

    /**
     * Install a context menu.
     */
    @Override
    public void onCreateContextMenu( ContextMenu menu, View v, ContextMenuInfo menuInfo )
    {
        menu.add( Menu.NONE, 1, Menu.NONE, "Clear" ).setAlphabeticShortcut( 'x' );
    }

    /**
     * Respond to a context menu selection.
     */
    @Override
    public boolean onContextItemSelected( MenuItem item )
    {
        switch ( item.getItemId() )
        {
            case 1:
                dotModel.clearDots();
                return true;
            default:
                ;
        }

        return false;
    }

    /**
     * @param dots the dots we're drawing
     * @param view the view in which we're drawing dots
     */
    void makeDot( Dots dots, DotView view )
    {
        int randomX = rand.nextInt( ( view.getWidth() / ( DOT_DIAMETER ) ) );
        int randomY = rand.nextInt( ( view.getHeight() / ( DOT_DIAMETER ) ) );
        Dot dot = new Dot( ( randomX * DOT_DIAMETER ) + DOT_RADIUS, ( randomY * DOT_DIAMETER ) + DOT_RADIUS, Color.GREEN, DOT_RADIUS, randomX, randomY );
        dots.addDot( dot, randomX, randomY, ( view.getWidth() / ( DOT_DIAMETER ) ), ( view.getHeight() / ( DOT_DIAMETER ) ) );
        ++MONSTERS;
        if ( MONSTERS == MAX_MONSTERS )
        {
            dotGenerator.monstersDone();
        }
    }

    void moveToNeighbors( Dots dots )
    {
        dots.moveToNeighbors();
        if ( dots.getDots().size() == 0 )
        {
            MAX_MONSTERS *= 2;
            ++LEVEL;
            MONSTERS = 0;
            dotGenerator.monstersKilled();
        }
    }

    /**
     * Listen for taps.
     */
    private static final class TrackingTouchListener implements View.OnTouchListener
    {
        private final Dots mDots;
        private final DotGenerator dotGenerator;
        private List<Integer> tracks = new ArrayList<Integer>();

        TrackingTouchListener( Dots dots, DotGenerator dotGenerator )
        {
            mDots = dots;
            this.dotGenerator = dotGenerator;
        }

        @Override
        public boolean onTouch( View v, MotionEvent evt )
        {
            int n;
            int idx;
            int action = evt.getAction();
            switch ( action & MotionEvent.ACTION_MASK )
            {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    idx = ( action & MotionEvent.ACTION_POINTER_INDEX_MASK ) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                    tracks.add( Integer.valueOf( evt.getPointerId( idx ) ) );
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    idx = ( action & MotionEvent.ACTION_POINTER_INDEX_MASK ) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                    tracks.remove( Integer.valueOf( evt.getPointerId( idx ) ) );
                    break;

                case MotionEvent.ACTION_MOVE:
                    n = evt.getHistorySize();
                    for ( Integer i : tracks )
                    {
                        idx = evt.findPointerIndex( i.intValue() );
                        for ( int j = 0 ; j < n ; j++ )
                        {
                            addDot( mDots, evt.getHistoricalX( idx, j ), evt.getHistoricalY( idx, j ), evt.getHistoricalPressure( idx, j ), evt.getHistoricalSize( idx, j ) );
                        }
                    }
                    break;


                default:
                    return false;
            }

            for ( Integer i : tracks )
            {
                idx = evt.findPointerIndex( i.intValue() );
                addDot( mDots, evt.getX( idx ), evt.getY( idx ), evt.getPressure( idx ), evt.getSize( idx ) );
            }

            return true;
        }

        private void addDot( Dots dots, float x, float y, float p, float s )
        {
            if ( dots.intersects( new Dot( x, y, Color.CYAN, ( int ) ( ( p + 0.5 ) * ( s + 0.5 ) * DOT_RADIUS ), 0, 0 ) ) )
            {
                dotView.invalidate();
            }
        }
    }

    /**
     * Generate new dots, one per second.
     */
    private final class DotGenerator implements Runnable
    {
        final Dots dots;
        final DotView view;

        private final Handler hdlr = new Handler();
        private final Runnable makeDots = new Runnable()
        {
            @Override
            public void run() { makeDot( dots, view ); }
        };
        private final Runnable moveToNeighbors = new Runnable()
        {
            @Override
            public void run() { moveToNeighbors( dots ); }
        };

        private volatile boolean done;

        DotGenerator( Dots dots, DotView view )
        {
            this.dots = dots;
            this.view = view;
        }

        public void monstersDone() { done = true; }

        public void monstersKilled() { done = false; }

        @Override
        public void run()
        {
            while ( !done )
            {
                while ( !done )
                {
                    hdlr.post( makeDots );
                    try
                    {
                        Thread.sleep( 400 );
                    }
                    catch ( InterruptedException e )
                    {
                    }
                }

                while ( done )
                {
                    hdlr.post( moveToNeighbors );
                    try
                    {
                        Thread.sleep( 1000 );
                    }
                    catch ( InterruptedException e )
                    {
                    }
                }
            }

            Log.d( "thread should die here", "" );
            return;
        }
    }
}