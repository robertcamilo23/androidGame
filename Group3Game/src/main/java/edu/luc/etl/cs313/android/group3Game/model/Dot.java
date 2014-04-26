package edu.luc.etl.cs313.android.group3Game.model;


/**
 * A dot: the coordinates, color and size.
 */
public final class Dot
{
    private final float x, y;
    private final int color;
    private final int radius;

    /**
     * @param x        horizontal coordinate.
     * @param y        vertical coordinate.
     * @param color    the color.
     * @param radius dot radius.
     */
    public Dot( float x, float y, int color, int radius )
    {
        this.x = x;
        this.y = y;
        this.color = color;
        this.radius = radius;
    }

    /**
     * @return the horizontal coordinate.
     */
    public float getX()
    { return x; }

    /**
     * @return the vertical coordinate.
     */
    public float getY()
    { return y; }

    /**
     * @return the color.
     */
    public int getColor()
    { return color; }

    /**
     * @return the dot radius.
     */
    public int getRadius()
    { return radius; }
}