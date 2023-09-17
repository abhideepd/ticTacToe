package com.algorist.TicTacToe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//@SpringBootApplication
public class TicTacToeApplication {

	public static void main(String[] args)throws IOException {
//		SpringApplication.run(TicTacToeApplication.class, args);
		int n=3;
		TicTacToe game=new TicTacToe(n);
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		while(2>0){
			System.out.println(game);
			System.out.println("Your Move, enter the row followed by column, below: \n");
			int row=Integer.parseInt(input.readLine());
			int col=Integer.parseInt(input.readLine());
			if(game.edgeCase(row, col)){
				System.out.println("Out of Bounds");
				continue;
			}
			if(game.alreadyMovedHere(row, col)){
				System.out.println("Already Moved, try again!");
				continue;
			}
			int result=game.move(row, col, 1);
			if(result==0){
				result=game.generateComputerMove();
				if(result!=0){
					System.out.println(game);
					System.out.println("I won, bitch _|_");
					break;
				}
			}
			else{
				System.out.println(game);
				System.out.println("You won :(");
				break;
			}
		}
	}
}
