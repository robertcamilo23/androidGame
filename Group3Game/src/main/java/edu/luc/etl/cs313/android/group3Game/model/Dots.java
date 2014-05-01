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
        intersectsMonster( dot );
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
                        int color = ( rand.nextBoolean() ) ? Color.GREEN : Color.YELLOW;
                        switch ( randomMovement )
                        {
                            case 0:
                                right( i, j, color );
                            case 1:
                                downRight( i, j, color );
                            case 2:
                                left( i, j, color );
                            case 3:
                                upRight( i, j, color );
                            case 4:
                                downLeft( i, j, color );
                            case 5:
                                down( i, j, color );
                            case 6:
                                upLeft( i, j, color );
                            case 7:
                                up( i, j, color );
                        }
                        notifyListener();
                    }
                }
            }
        }
    }

    private void upLeft( int i, int j, int color )
    {
        if ( i - 1 > 0 && j - 1 > 0 )
        {
            if ( null == dotMatrix[ i - 1 ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY() - DOT_DIAMETER, color, DOT_RADIUS, dot.getxM() - 1, dot.getyM() - 1 );
                dotMatrix[ i - 1 ][ j - 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void up( int i, int j, int color )
    {
        if ( j - 1 > 0 )
        {
            if ( null == dotMatrix[ i ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX(), dot.getY() - DOT_DIAMETER, color, DOT_RADIUS, dot.getxM(), dot.getyM() - 1 );
                dotMatrix[ i ][ j - 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void upRight( int i, int j, int color )
    {
        if ( i + 1 < getWidth() && j - 1 > 0 )
        {
            if ( null == dotMatrix[ i + 1 ][ j - 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY() - DOT_DIAMETER, color, DOT_RADIUS, dot.getxM() + 1, dot.getyM() - 1 );
                dotMatrix[ i + 1 ][ j - 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void downLeft( int i, int j, int color )
    {
        if ( i - 1 > 0 && j + 1 < getHeight() )
        {
            if ( null == dotMatrix[ i - 1 ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY() + DOT_DIAMETER, color, DOT_RADIUS, dot.getxM() - 1, dot.getyM() + 1 );
                dotMatrix[ i - 1 ][ j + 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void down( int i, int j, int color )
    {
        if ( j + 1 < getHeight() )
        {
            if ( null == dotMatrix[ i ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX(), dot.getY() + DOT_DIAMETER, color, DOT_RADIUS, dot.getxM(), dot.getyM() + 1 );
                dotMatrix[ i ][ j + 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void downRight( int i, int j, int color )
    {
        if ( i + 1 < getWidth() && j + 1 < getHeight() )
        {
            if ( null == dotMatrix[ i + 1 ][ j + 1 ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY() + DOT_DIAMETER, color, DOT_RADIUS, dot.getxM() + 1, dot.getyM() + 1 );
                dotMatrix[ i + 1 ][ j + 1 ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void left( int i, int j, int color )
    {
        if ( i - 1 > 0 )
        {
            if ( null == dotMatrix[ i - 1 ][ j ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() - DOT_DIAMETER, dot.getY(), color, DOT_RADIUS, dot.getxM() - 1, dot.getyM() );
                dotMatrix[ i - 1 ][ j ] = newDot;
                if ( dots.remove( dot ) )
                {
                    dots.add( newDot );
                }
                dotMatrix[ i ][ j ] = null;
            }
        }
    }

    private void right( int i, int j, int color )
    {
        if ( i + 1 < getWidth() )
        {
            if ( null == dotMatrix[ i + 1 ][ j ] && null != dotMatrix[ i ][ j ] )
            {
                Dot dot = dotMatrix[ i ][ j ];
                Dot newDot = new Dot( dot.getX() + DOT_DIAMETER, dot.getY(), color, DOT_RADIUS, dot.getxM() + 1, dot.getyM() );
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
        for ( int i = 0 ; i < width ; i++ )
        {
            for ( int j = 0 ; j < height ; j++ )
            {
                dotMatrix[ i ][ j ] = null;
            }
        }
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

    public boolean intersectsMonster( Dot userTouch )
    {
        for ( Dot dot : dots )
        {
            if ( dot.getX() - ( dot.getRadius() ) <= userTouch.getX() && userTouch.getX() <= dot.getX() + ( dot.getRadius() ) &&
                 dot.getY() - ( dot.getRadius() ) <= userTouch.getY() && userTouch.getY() <= dot.getY() + ( dot.getRadius() ) )
            {
                dotMatrix[ dot.getxM() ][ dot.getyM() ] = null;
                dots.remove( dot );
                return true;
            }
        }
        return false;
    }

    public boolean intersectsVulnerableMonster( Dot userTouch )
    {
        for ( Dot dot : dots )
        {
            if ( dot.getX() - ( dot.getRadius() ) <= userTouch.getX() && userTouch.getX() <= dot.getX() + ( dot.getRadius() ) &&
                 dot.getY() - ( dot.getRadius() ) <= userTouch.getY() && userTouch.getY() <= dot.getY() + ( dot.getRadius() ) &&
                 dot.getColor() == Color.YELLOW )
            {
                dotMatrix[ dot.getxM() ][ dot.getyM() ] = null;
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