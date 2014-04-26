package edu.luc.etl.cs313.android.group3Game.model;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * A list of dots.
 */
public class Dots
{


    private final LinkedList<Dot> dots = new LinkedList<Dot>();
    private final List<Dot> safeDots = Collections.unmodifiableList( dots );
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
     * @param x        dot horizontal coordinate.
     * @param y        dot vertical coordinate.
     * @param color    dot color.
     * @param diameter dot size.
     */
    public void addDot( float x, float y, int color, int diameter )
    {
        dots.add( new Dot( x, y, color, diameter ) );
        notifyListener();
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
                 dot.getY() - ( dot.getRadius() ) <= userTouch.getY() && userTouch.getY() <= dot.getY() + ( dot.getRadius() ) )
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