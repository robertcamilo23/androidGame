package com.oreilly.demo.android.pa.uidemo.model;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Random;






/** A list of dots. */
public class Dots {
    public Dot[][] arraySquare;
    public Cell[][] arrayCell;
    private final LinkedList<Cell> listCell = new LinkedList<Cell>();
    public int n,m;

    public Dots(int n, int m) {
        this.n = n;
        this.m = m;
        int id = 0;
        arraySquare = new Dot[n][m];
        arrayCell = new Cell[n][m];
        Cell cel;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cel = new Cell(i, j, id);
                arrayCell[i][j] = cel;
                listCell.add(cel);
                id++;
            }
        }
    }

    public int getN(){return n;}

    public int getM(){return m;}


    public void addDot1() {

    }


    public int checkCellId(float y, float x) {

        double quotientX = Math.floor(x/Constants.squaresize);
        double quotientY = Math.floor(y/Constants.squaresize);
        int thisCellID = -1;

        for(Cell cel: getCells()){
            if(cel.getI()== quotientX && cel.getJ() == quotientY){
                thisCellID = cel.getId();
            }
        }
        return thisCellID;
    }

    public AtomicReference getCells() {
        return getCells();
    }

    /** DotChangeListener. */
    public interface DotsChangeListener {
        /** @param dots the dots that changed. */
        void onDotsChange(Dots dots);
    }

    private final LinkedList<Dot> dots = new LinkedList<Dot>();
    private final List<Dot> safeDots = Collections.unmodifiableList(dots);

    private DotsChangeListener dotsChangeListener;

    /** @param l set the change listener. */
    public void setDotsChangeListener(DotsChangeListener l) {
        dotsChangeListener = l;
    }

    /** @return the most recently added dot. */
    public Dot getLastDot() {
        return (dots.size() <= 0) ? null : dots.getLast();
    }

    /** @return immutable list of dots. */
    public List<Dot> getDots() { return safeDots; }

    /**
     * @param x dot horizontal coordinate.
     * @param y dot vertical coordinate.
     * @param color dot color.
     * @param diameter dot size.
     */
    public void addDot(float x, float y, int color, int diameter) {
        dots.add(new Dot(x, y, color, diameter));
        notifyListener();
    }

    /** Remove all dots. */
    public void clearDots() {
        dots.clear();
        notifyListener();
    }

    private void notifyListener() {
        if (null != dotsChangeListener) {
            dotsChangeListener.onDotsChange(this);
        }
    }
}