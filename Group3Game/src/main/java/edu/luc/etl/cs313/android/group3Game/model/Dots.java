package edu.luc.etl.cs313.android.group3Game.model;


import android.graphics.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static edu.luc.etl.cs313.android.group3Game.model.Constants.*;


/**
 * A list of dots.
 */
public class Dots
{

    private final LinkedList<Dot> dots = new LinkedList<Dot>();
    private final List<Dot> safeDots = Collections.unmodifiableList( dots );
    private Dot[][] dotMatrix;
    private int width;
    private int height;
    private final Random rand = new Random();
    private DotsChangeListener dotsChangeListener;

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
            dotMatrix = new Dot[ this.getWidth() ][ this.getHeight() ];
        }
        dotMatrix[ randomX ][ randomY ] = dot;
        dots.add( dot );
        notifyListener();
    }

    public void moveToNeighbors()
    {
        if ( !dots.isEmpty() )
        {
            for ( int i = 0 ; i < getWidth() ; i++ )
            {
                for ( int j = 0 ; j < getHeight() ; j++ )
                {
                    if ( null != dotMatrix[ i ][ j ] )
                    {
                        int randomMovement = rand.nextInt( 8 );
                        switch ( randomMovement )
                        {
                            case 0:
                                right( i, j );
                            case 1:
                                upLeft( i, j );
                            case 2:
                                up( i, j );
                            case 3:
                                upRight( i, j );
                            case 4:
                                downLeft( i, j );
                            case 5:
                                down( i, j );
                            case 6:
                                downRight( i, j );
                            case 7:
                                left( i, j );
                        }
                        notifyListener();
                    }
                }
            }
        }
    }

    private void upLeft( int i, int j )
    {
        if ( i - 1 > 0 && j - 1 > 0 )
        {
            if ( null == dotMatrix[ i - 1 ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY() - DOT_DIAMETER, Color.YELLOW, DOT_RADIUS, dot.getxM() - 1, dot.getyM() - 1 );
                dotMatrix[ i - 1 ][ j - 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void up( int i, int j )
    {
        if ( j - 1 > 0 )
        {
            if ( null == dotMatrix[ i ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX(), dot.getY() - DOT_DIAMETER, Color.YELLOW, DOT_RADIUS, dot.getxM(), dot.getyM() - 1 );
                dotMatrix[ i ][ j - 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void upRight( int i, int j )
    {
        if ( i + 1 < getWidth() && j - 1 > 0 )
        {
            if ( null == dotMatrix[ i + 1 ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY() - DOT_DIAMETER, Color.YELLOW, DOT_RADIUS, dot.getxM() + 1, dot.getyM() - 1 );
                dotMatrix[ i + 1 ][ j - 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void downLeft( int i, int j )
    {
        if ( i - 1 > 0 && j + 1 < getHeight() )
        {
            if ( null == dotMatrix[ i - 1 ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY() + DOT_DIAMETER, Color.YELLOW, DOT_RADIUS, dot.getxM() - 1, dot.getyM() + 1 );
                dotMatrix[ i - 1 ][ j + 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void down( int i, int j )
    {
        if ( j + 1 < getHeight() )
        {
            if ( null == dotMatrix[ i ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX(), dot.getY() + DOT_DIAMETER, Color.YELLOW, DOT_RADIUS, dot.getxM(), dot.getyM() + 1 );
                dotMatrix[ i ][ j + 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void downRight( int i, int j )
    {
        if ( i + 1 < getWidth() && j + 1 < getHeight() )
        {
            if ( null == dotMatrix[ i + 1 ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY() + DOT_DIAMETER, Color.YELLOW, DOT_RADIUS, dot.getxM() + 1, dot.getyM() + 1 );
                dotMatrix[ i + 1 ][ j + 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void left( int i, int j )
    {
        if ( i - 1 > 0 )
        {
            if ( null == dotMatrix[ i - 1 ][ j ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY(), Color.YELLOW, DOT_RADIUS, dot.getxM() - 1, dot.getyM() );
                dotMatrix[ i - 1 ][ j ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void right( int i, int j )
    {
        if ( i + 1 < getWidth() )
        {
            if ( null == dotMatrix[ i + 1 ][ j ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY(), Color.YELLOW, DOT_RADIUS, dot.getxM() + 1, dot.getyM() );
                dotMatrix[ i + 1 ][ j ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
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

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
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