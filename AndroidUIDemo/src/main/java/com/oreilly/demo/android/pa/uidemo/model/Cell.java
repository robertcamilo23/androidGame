package com.oreilly.demo.android.pa.uidemo.model;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Hamdan on 4/18/14.
 */
public class Cell {
    private int cycleMove;
    private int id;
    /** a list of all the neighbors**/
    private LinkedList<Cell> neighborList;

    /** index position on the grid**/
    private int i,j;
    private Dot dot;

    private Random random = new Random();

    public Cell(int i, int j, int id) {
        this.i = i;
        this.j = j;
        this.id = id;
        neighborList = new LinkedList<Cell>();
        dot = null;
    }

    public int getId() {return id ;}

    public int getI(){return i;}

    public int getJ(){return j;}

    public Dot getDot(){return dot;}

    public int getCycleMove(){return cycleMove;}

    public void decCycleMove() {cycleMove--;}

    public void addMonster (Dot dot){
        if (dot != null){
            float k = i * Constants.squaresize + (Constants.squaresize/2);
            float l = j * Constants.squaresize + (Constants.squaresize/2);
            dot.setCoordinate(k,l);
            dot.setId(this.id);
            this.dot = dot;
            this.cycleMove = random.nextInt(6);

        }
    }
    public void addNeighbor (Cell cell) {neighborList.add(cell) ;}
    public LinkedList<Cell> getNeighborList(){return neighborList;}

    public Cell getRandomNeighbor(LinkedList<Cell> list) {

        if (list.size() == 0)
            return null;
        else
            return list.get(random.nextInt(list.size()));

    }

     public void changePositionMonster() {
     LinkedList<Cell> list= neighborList;
     int listSize = list.size();
         int i=0;
         if (list.size() != 0){
             Cell newCel = this .getRandomNeighbor(neighborList);
             while (i<listSize){

                 if (newCel.getDot()== null) break;
                list.remove(newCel);
                 newCel = this.getRandomNeighbor(list);
                 i++;
             }
             if(i<listSize){
                 newCel.addMonster(dot);
                 this.dot = null;
             }
         }
     }

      public void remove(){
         dot = null;
          }
      public void addAllNeighbors(Dots dots) {
          int i = this.getI();
          int j = this.getJ();
          Cell[][] arrayCell = dots.getArrayCell();
          int n = dots.getN();
          int m = dots.getM();

          if(i-1>=0){
              if(arrayCell[i-1][j].getDot() == null){
                  this.addNeighbor(arrayCell[i - 1][j]);
              }
              if(j-1>=0)
                  if(arrayCell[i-1][j-1].getDot() == null)
                      this.addNeighbor(arrayCell[i-1][j-1]);
              if(j+1<m)
                  if(arrayCell[i-1][j+1].getDot() == null)
                      this.addNeighbor(arrayCell[i-1][j+1]);
          }
          if(i+1<n){
              if(arrayCell[i+1][j].getDot() == null)
                  this.addNeighbor(arrayCell[i+1][j]);
              if(j-1>=0)
                  if(arrayCell[i+1][j-1].getDot() == null)
                      this.addNeighbor(arrayCell[i+1][j-1]);
              if(j+1<m)
                  if(arrayCell[i+1][j+1].getDot() == null)
                      this.addNeighbor(arrayCell[i+1][j+1]);
          }
          if(j-1>=0)
              if(arrayCell[i][j-1].getDot() == null)
                  this.addNeighbor(arrayCell[i][j-1]);
          if(j+1<m)
              if(arrayCell[i][j+1].getDot() == null)
                  this.addNeighbor(arrayCell[i][j+1]);
          }
      }






