/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication19;

import java.util.Scanner;

/**
 *
 * @author Zeynep
 */
public class JavaApplication19 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        char[][] labirent = {//!=10,R=5,T=5,F=5,H=5,
            {'#', '!', '.', '.', 'R', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
            {'.', '.', '#', '.', '.', '.', '#', '.', 'H', '.', '.', '.', '.', '.', '!'},
            {'F', '.', '.', '.', '#', '.', '!', '.', '.', 'R', '.', '.', '#', '#', '.'},
            {'.', '.', '#', '.', '.', '#', '.', '.', '.', '.', 'F', '.', '.', '.', '.'},
            {'.', '!', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '.', '.', '.'},
            {'.', '.', 'H', '.', '.', '!', '.', '.', 'H', '.', '.', 'F', '.', '.', 'R'},
            {'#', '#', '#', '#', '.', '.', '#', '.', '.', '.', 'T', '.', '.', '.', 'E'},
            {'.', '.', '#', '.', 'F', '.', '#', '#', '.', '#', '#', '#', '#', '.', '.'},
            {'.', '#', '.', '.', '.', '.', '!', '.', '#', '.', '.', '.', '#', '.', '.'},
            {'.', 'T', 'T', '.', '#', '#', '.', '.', '.', '.', 'T', '.', '.', '.', 'R'},
            {'.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '#', '.', 'T', '.'},
            {'B', '.', '#', '.', '.', '!', '.', '!', '.', '.', '.', '.', '.', '.', '#'},
            {'.', '.', '.', 'F', '!', '.', '.', '.', 'H', '.', '.', 'R', '.', '.', '.'},
            {'.', '.', 'H', '.', '.', '.', '!', '.', '.', '.', '#', '.', '.', '#', '.'},
            {'.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '#'}};
        for (int h = 0; h < labirent.length; h++) {// we begin by finding the coordinat of B and adding e in its place
                for (int j = 0; j < labirent[h].length; j++) {
                    if ('B' == labirent[h][j]) {
                         labirent[h][j]='e';
                    }
                }
            }
        int up = 0, right = 0, up1 = 0, right1 = 0;//i made up ,up,right and right1 
        Scanner input = new Scanner(System.in);//i take input from the user
        int[] tools = {0, 0, 0, 0};//an array to collect the bounces
        int[] moves = {0,0};//the moves to count how many moves the user dose 
        for (int i = 0; i > -1;) {//made a loop that dosent end onless if i is negative
            moves[0] = moves[0] + 1;//moves go up every time the loop starts
            if (moves[0] % 5 == 0) {//if the user makes 5 moves every bounice and mine gets shuffeld in the map
                transport(labirent);
            }
            for (int h = 0; h < labirent.length; h++) {// finding the coordiantes of e
                for (int j = 0; j < labirent[h].length; j++) {
                    if ('e' == labirent[h][j]) {
                        up = h;
                        right = j;
                    }
                }
            }
            up1 = up;
            right1 = right;
            screen(labirent);//priniting the screen in a method
            System.out.println("=================================");
            System.out.println(" Welcome to legend of \"e\"");
            System.out.println(" (" + up1 + " /" + right1 + ")");
            System.out.println(" moves=" + moves[0] + "");
            System.out.println(" to move use wasd   ");
            System.out.println(" 1:T=" + tools[0] + " 2:H=" + tools[1] + " 3:R=" + tools[2] + " 4:F=" + tools[3]);
            System.out.println("================================");
            
            String S = input.next();//we take an input from the user
            switch (S) {//the compyter looks at the input
                case "w": {//before we send the coordinates to the method we change the value of right or up
                    up--;
                    control(labirent, up, right, up1, right1, tools, moves);
                    break;
                }
                case "s": {
                    up++;
                    control(labirent, up, right, up1, right1, tools, moves);
                    break;
                }
                case "a": {
                    right--;
                    control(labirent, up, right, up1, right1, tools, moves);
                    break;
                }
                case "d": {
                    right++;
                    control(labirent, up, right, up1, right1, tools, moves);
                    break;
                }
                case "t": {//when the user press 1 he uses T bounes 
                    if (tools[0] > 0) {
                        System.out.print("Enter numbers x and y between 14 and 0");//asking the use for coordinates
                        int x = input.nextInt();
                        int y = input.nextInt();
                        if (x >= 0 && x < 15 && y >= 0 && y < 15) {//we look at the x and y coordinates if they ar negative or outside the map
                            if (labirent[y][x] == '#' || labirent[y][x] == '!') {
                                System.out.println("you cant tp there");
                                break;
                            } else {
                                tools[0] = tools[0] - 1;
                                control(labirent, y, x, up1, right1, tools, moves);
                            }
                        } else {
                            System.out.println("the coordinates are wrong");
                        }
                    } else {//this well activate when the user dosent have T bounce
                        System.out.println("you dont have \"T\"");
                        break;
                    }
                    break;
                }
                case "h": {//when the use press 2 he uses H bounes
                    if (tools[1] >= 1) {
                        tools[1] = tools[1] - 1;
                        moves[0] = moves[0] - 2;
                        break;
                    } else {//this well activate when the user dosent have H bounce
                        System.out.println("you dont have \"H\"");
                    }
                }

            }
            i = moves[1];
        }
    }

    public static void screen(char[][] maze) {//tghis methode prints the maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println("");
        }
    }

    public static void control(char maze[][], int up, int right, int up1, int right1, int[] tools, int[] moves) {//this method checks what is in youer way
        int flag = 0;
        if (up < 0 || right < 0 || up > 14 || right > 14) {//we check if the coordinates are out of the map
            System.out.println("wrong");
            flag++;
        }
        if (flag == 0) {//if the coordinates are not out of the map we countiue to check
            switch (maze[up][right]) {
                case '.': {
                    maze[up][right] = 'e';
                    maze[up1][right1] = '.';
                    break;
                }
                case '#': {
                    if (tools[2] > 0) {
                        tools[2] = tools[2] - 1;
                        maze[up][right] = 'e';
                        maze[up1][right1] = '.';
                    }
                    break;
                }
                case 'T': {
                    tools[0] = tools[0] + 1;
                    maze[up][right] = 'e';
                    maze[up1][right1] = '.';
                    break;
                }
                case 'R': {
                    tools[2] = tools[2] + 1;
                    maze[up][right] = 'e';
                    maze[up1][right1] = '.';
                    break;
                }
                case 'H': {
                    tools[1] = tools[1] + 1;
                    maze[up][right] = 'e';
                    maze[up1][right1] = '.';
                    break;
                }
                case 'F': {
                    tools[3] = tools[3] + 1;
                    maze[up][right] = 'e';
                    maze[up1][right1] = '.';
                    break;
                }
                case '!': {
                    if (tools[3] > 0) {
                        tools[3] = tools[3] - 1;
                        maze[up][right] = 'e';
                        maze[up1][right1] = '.';
                        break;
                    }
                    if (tools[2] == 0) {
                        maze[up][right] = 'e';
                        maze[up1][right1] = '.';
                        moves[0] = moves[0] + 5;
                        break;
                    }
                }
                case 'E': {
                    moves[1] = -100;
                }
            }
        }
    }

    public static void transport(char maze[][]) {//transport dletes every bounice and add them to a an arrey
        int[] bounce = {0, 0, 0, 0, 0,};
        for (int i = maze.length - 1; i >= 0; i--) {//we look at the maze to check for the bounicec and delete them
            for (int j = maze[i].length - 1; j >= 0; j--) {
                switch (maze[i][j]) {
                    case 'T': {
                        maze[i][j] = '.';
                        bounce[0] += 1;
                        break;
                    }
                    case 'H': {
                        maze[i][j] = '.';
                        bounce[1] += 1;
                        break;
                    }
                    case 'R': {
                        maze[i][j] = '.';
                        bounce[2] += 1;
                        break;
                    }
                    case 'F': {
                        maze[i][j] = '.';
                        bounce[3] += 1;
                        break;
                    }
                    case '!': {
                        maze[i][j] = '.';
                        bounce[4] += 1;
                        break;
                    }

                }
            }
        }
        char[] harflar = {'T', 'H', 'R', 'F', '!'};
        for (int i = bounce.length - 1; i >= 0; i--) {//we add the bounicec to the maze
            for (int s = bounce[i]; s > 0; s--) {
                int up = (int) (Math.random() * 15);
                int right = (int) (Math.random() * 15);
                if (maze[up][right] == '.') {
                    bounce[i] = bounce[i] - 1;
                    maze[up][right] = harflar[i];
                } else {
                    s++;
                }
            }
        }
    }
}
