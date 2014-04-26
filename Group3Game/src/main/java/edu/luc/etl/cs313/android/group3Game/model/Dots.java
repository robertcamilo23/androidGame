package edu.luc.etl.cs313.android.group3Game.model;


import android.graphics.Color;
import android.util.Log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * A list of dots.
 */
public class Dots
{

    public static final int DOT_RADIUS = 44;
    public static final int DOT_DIAMETER = DOT_RADIUS * 2;
    private final LinkedList<Dot> dots = new LinkedList<Dot>();
    private final List<Dot> safeDots = Collections.unmodifiableList( dots );
    private Dot[][] dotMatrix;
    private int width, height;
    private final Random rand = new Random();
    private DotsChangeListener dotsChangeListener;

    public void addDot1()
    {

    }

    /**
     * @param l set the change listener.
     */
    public void setDotsChangeListener( DotsChangeListener l )
    {
        dotsChangeListener = l;
    }

    /**
     * @return the most recently added dot.
     */
    public Dot getLastDot()
    {
        return ( dots.size() <= 0 ) ? null : dots.getLast();
    }

    /**
     * @return immutable list of dots.
     */
    public List<Dot> getDots()
    { return safeDots; }

    /**
     * @param randomX
     * @param randomY
     * @param width
     * @param height
     */
    public void addDot( Dot dot, int randomX, int randomY, int width, int height )
    {
        if ( null == dotMatrix )
        {
            this.width = width;
            this.height = height;
            dotMatrix = new Dot[ this.width ][ this.height ];
        }
        dotMatrix[ randomX ][ randomY ] = dot;
        dots.add( dot );
        notifyListener();
    }

    public void moveToNeighbors()
    {
        for ( int i = 0 ; i < 5 ; i++ )
        {
            int nextInt = rand.nextInt( dots.size() );

            Dot dot = dots.get( nextInt );

            if ( upLeft( dot.getxM(), dot.getyM() ) )
            {
            }
            else if ( up( dot.getxM(), dot.getyM() ) )
            {
            }
            else if ( upRight( dot.getxM(), dot.getyM() ) )
            {
            }
            else if ( downLeft( dot.getxM(), dot.getyM() ) )
            {
            }
            else if ( down( dot.getxM(), dot.getyM() ) )
            {
            }
            else if ( downRight( dot.getxM(), dot.getyM() ) )
            {
            }
            else if ( left( dot.getxM(), dot.getyM() ) )
            {
            }
            else if ( right( dot.getxM(), dot.getyM() ) )
            {
            }
            notifyListener();
        }
    }

    private boolean upLeft( int i, int j )
    {
        if ( i - 1 > 0 && j - 1 > 0 )
        {
            if ( null == dotMatrix[ i - 1 ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY() - DOT_DIAMETER, Color.GREEN, DOT_RADIUS, dot.getxM() - 1, dot.getyM() - 1 );
                dotMatrix[ i - 1 ][ j - 1 ] = newDot;
                dots.remove( dot );
                dots.add( newDot );
                dotMatrix[ i ][ j ] = null;
                return true;
            }
        }
        return false;
    }

    private boolean up( int i, int j )
    {
        if ( j - 1 > 0 )
        {
            if ( null == dotMatrix[ i ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX(), dot.getY() - DOT_DIAMETER, Color.GREEN, DOT_RADIUS, dot.getxM(), dot.getyM() - 1 );
                dotMatrix[ i ][ j - 1 ] = newDot;
                dots.remove( dot );
                dots.add( newDot );
                dotMatrix[ i ][ j ] = null;
                return true;
            }
        }
        return false;
    }

    private boolean upRight( int i, int j )
    {
        if ( i + 1 < width && j - 1 > 0 )
        {
            if ( null == dotMatrix[ i + 1 ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY() - DOT_DIAMETER, Color.GREEN, DOT_RADIUS, dot.getxM() + 1, dot.getyM() - 1 );
                dotMatrix[ i + 1 ][ j - 1 ] = newDot;
                dots.remove( dot );
                dots.add( newDot );
                dotMatrix[ i ][ j ] = null;
                return true;
            }
        }
        return false;
    }

    private boolean downLeft( int i, int j )
    {
        if ( i - 1 > 0 && j + 1 < height )
        {
            if ( null == dotMatrix[ i - 1 ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY() + DOT_DIAMETER, Color.GREEN, DOT_RADIUS, dot.getxM() - 1, dot.getyM() + 1 );
                dotMatrix[ i - 1 ][ j + 1 ] = newDot;
                dots.remove( dot );
                dots.add( newDot );
                dotMatrix[ i ][ j ] = null;
                return true;
            }
        }
        return false;
    }

    private boolean down( int i, int j )
    {
        if ( j + 1 < height )
        {
            if ( null == dotMatrix[ i ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX(), dot.getY() + DOT_DIAMETER, Color.GREEN, DOT_RADIUS, dot.getxM(), dot.getyM() + 1 );
                dotMatrix[ i ][ j + 1 ] = newDot;
                dots.remove( dot );
                dots.add( newDot );
                dotMatrix[ i ][ j ] = null;
                return true;
            }
        }
        return false;
    }

    private boolean downRight( int i, int j )
    {
        if ( i + 1 < width && j + 1 < height )
        {
            if ( null == dotMatrix[ i + 1 ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY() + DOT_DIAMETER, Color.GREEN, DOT_RADIUS, dot.getxM() + 1, dot.getyM() + 1 );
                dotMatrix[ i + 1 ][ j + 1 ] = newDot;
                dots.remove( dot );
                dots.add( newDot );
                dotMatrix[ i ][ j ] = null;
                return true;
            }
        }
        return false;
    }

    private boolean left( int i, int j )
    {
        if ( i - 1 > 0 )
        {
            if ( null == dotMatrix[ i - 1 ][ j ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY(), Color.GREEN, DOT_RADIUS, dot.getxM() - 1, dot.getyM() );
                dotMatrix[ i - 1 ][ j ] = newDot;
                dots.remove( dot );
                dots.add( newDot );
                dotMatrix[ i ][ j ] = null;
                return true;
            }
        }
        return false;
    }

    private boolean right( int i, int j )
    {
        if ( i + 1 < width )
        {
            if ( null == dotMatrix[ i + 1 ][ j ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY(), Color.GREEN, DOT_RADIUS, dot.getxM() + 1, dot.getyM() );
                dotMatrix[ i + 1 ][ j ] = newDot;
                dots.remove( dot );
                dots.add( newDot );
                dotMatrix[ i ][ j ] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Remove all dots.
     */
    public void clearDots()
    {
        dots.clear();
        notifyListener();
    }

    private void notifyListener()
    {
        if ( null != dotsChangeListener )
        {
            dotsChangeListener.onDotsChange( this );
        }
    }

    public boolean intersects( Dot userTouch )
    {
        for ( Dot dot : dots )
        {
            if ( dot.getX() - ( dot.getRadius() ) <= userTouch.getX() && userTouch.getX() <= dot.getX() + ( dot.getRadius() ) &&
                 dot.getY() - ( dot.getRadius() ) <= userTouch.getY() && userTouch.getY() <= dot.getY() + ( dot.getRadius() ) &&
                 dot.getColor() == Color.YELLOW )
            {
                dots.remove( dot );
                return true;
            }
        }
        return false;
    }

    /**
     * DotChangeListener.
     */
    public interface DotsChangeListener
    {
        /**
         * @param dots the dots that changed.
         */
        void onDotsChange( Dots dots );
    }
}