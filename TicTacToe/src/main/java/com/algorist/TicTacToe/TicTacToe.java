package com.algorist.TicTacToe;

import java.util.ArrayList;
import java.util.HashSet;

public class TicTacToe {
    private int grid[][];
    private int gridSize;

    private int r[];
    private int c[];
    private int diognalSum;
    private int antiDiognalSum;
    private HashSet<Integer> columnsUnfilled;
    public TicTacToe(int n){
        r=new int[n];
        c=new int[n];
        grid=new int[n][n];
        diognalSum=0;
        antiDiognalSum=0;
        gridSize=n;
        columnsUnfilled=new HashSet<>();
        for(int row=0;row<gridSize;row++){
            for(int col=0;col<gridSize;col++){
                columnsUnfilled.add(hash(row, col));
            }
        }
    }
    private int hash(int row, int col){
        return (row+1)*10+(col+1);
    }
    public int move(int row, int col, int player){
        if(row>=gridSize || col>=gridSize){
            return 0;
        }
        columnsUnfilled.remove(hash(row, col));
        grid[row][col]=player==1?1:-1;
        if(check(row, col, grid[row][col])){
            return player;
        }
        return 0;
    }
    public int generateComputerMove(){
        int randomNo=((int)(Math.random()*10000))%columnsUnfilled.size();
//        System.out.println("--- -dfdd "+randomNo);
        ArrayList<Integer> arr=new ArrayList<>(columnsUnfilled);
        int hash=arr.remove(randomNo);
        columnsUnfilled.remove(hash);
        int col=(hash%10)-1;
        int row=(hash/10)-1;
        System.out.println("Computer's move: ("+row+", "+col+")");
        return move(row, col, 2);
    }
    public boolean edgeCase(int row, int col){
        if(row<0 || col<0 || row>=gridSize ||col>=gridSize){
            return true;
        }
        return false;
    }
    public boolean alreadyMovedHere(int row, int col){
        if(grid[row][col]==0){
            return false;
        }
        return true;
    }
    @Override
    public String toString(){
        String s="";
        for(int row=0;row<gridSize;row++){
            for(int col=0;col<gridSize;col++){
                if(grid[row][col]==1){
                    s+="X"+"  ";
                }
                else if(grid[row][col]==-1){
                    s+="O"+"  ";
                }
                else{
                    s+="_"+"  ";
                }
            }
            s+="\n";
        }
        return s;
    }
    private boolean check(int row, int col, int player){
//        return checkMethodOne(row, col, player);
        return checkMethodTwo(row, col, player);
    }

    private boolean checkMethodTwo(int row, int col, int player){
        r[col]+=player;
        c[row]+=player;
        if(row+col==gridSize-1)antiDiognalSum+=player;
        if(row==col)diognalSum+=player;
        if(Math.abs(r[col])==gridSize)return true;
        if(Math.abs(c[row])==gridSize)return true;
        if(Math.abs(diognalSum)==gridSize)return true;
        if(Math.abs(antiDiognalSum)==gridSize)return true;
        return false;
    }
    private boolean checkMethodOne(int row, int col, int player){
        if(checkHorizontal(col, player)){
            return true;
        }
        if(checkVertical(row, player)){
            return true;
        }
        if(checkDiognal(player)){
            return true;
        }
        if(checkAntiDiognal(player)){
            return true;
        }
        return false;
    }
    private boolean checkHorizontal(int col, int player){
        for(int row=0;row<gridSize;row++){
            if(grid[row][col]!=player){
                return false;
            }
        }
        return true;
    }
    private boolean checkVertical(int row, int player){
        for(int col=0;col<gridSize;col++){
            if(grid[row][col]!=player){
                return false;
            }
        }
        return true;
    }
    private boolean checkDiognal(int player){
        for(int n=0;n<gridSize;n++){
            if(grid[n][n]!=player){
                return false;
            }
        }
        return true;
    }
    private boolean checkAntiDiognal(int player){
        for(int n=0;n<gridSize;n++){
            if(grid[n][gridSize-n-1]!=player){
                return false;
            }
        }
        return true;
    }
}
