import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public  class Maze{
    // ANSI escape code for red text
    public static final String ANSI_RED = "\u001B[31m";
    // ANSI escape code to reset text color
    public static final String ANSI_RESET = "\u001B[0m";
    private char[][] maze; // 2D array to represent the maze
    private String mazeName; // Name of the maze
    private String mazeFileName; // File name of the maze
    
    // Constructor for the Maze class
    // It takes the name of the maze file as an argument
    public Maze(String mazeName){ 
        mazeFileName = mazeName; // Store the file name
        // Trim the maze name and store it
        this.mazeName = trimMazeName(mazeName);
    }

    // Method to print the maze
    public void getMaze(){ 
        printMaze(maze);
    }

    // Method to read the maze from the file and store it in the 2D array
    public void setMaze()throws IOException{ 
        this.maze = readMaze(mazeFileName);
    }

    // Getter method for the maze 2D array
    public char[][] getMazeMap(){ 
        return maze;
    }
    // Getter method for the maze name
    public String getMazeName(){ 
        return mazeName;
    }

    public int[] startPosition(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int p = 0; p< maze[0].length; p++) {
                if (maze[i][p] == 'E' || maze[i][p] == 'L' || maze[i][p] == 'R' || maze[i][p] == 'D' ||maze[i][p] == 'U') {
                    return new int[]{i, p};
                }
            }
        }
        return null;
    }

    public boolean canFinish(char[][] maze, int x, int y) {
        if (maze[x][y] == 'G') {
            return true;
        }
        maze[x][y] = 'X'; // Mark the current position as visited
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
        for (int i = 0; i < directions.length; i++){
            int newX = x + directions[i][0], newY = y + directions[i][1];
            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && maze[newX][newY] != 'X' && canFinish(maze, newX, newY)) {
                return true;
            }
        }
        return false;
    }

    public  static String trimMazeName(String mazeName){
        String str = mazeName;
        String[] parts = str.split("/");
        str = parts[1].trim();
        String[] parts2 = str.split("\\.");
        str = parts2[0].trim();
        return str;
    }

    public static boolean checkMaze(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        if (reader.readLine() == null) {
            reader.close();
            return true;
        } else {
            reader.close();
            return false;
        }        
    }

    // Method to read the maze from a file
    public static char[][] readMaze(String path) throws IOException{ //Read the maze from a file
        FileReader fR = new FileReader(path);
        BufferedReader reader = new BufferedReader(fR);
        String line = reader.readLine();
        String[] size = line.split("x");
        char[][] maze = new char[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
        for(int i = 0; i< maze.length; i++){
            line = reader.readLine();
            for(int o =0;o<maze[i].length;o++){
                char posi = line.charAt(o);
                maze[i][o] = posi;
            }
        }
        fR.close();
        reader.close();
        return maze;
    }
    // Method to print the maze
    public static void printMaze(char[][] maze){ //Print the maze translated to a graphic representation
        for(int i = 0; i< maze.length; i++){            
            for(int p =0;p<maze[i].length;p++){
                // Print the maze with different characters for different directions
                // and different types of cells
                if( maze[i][p]=='R' || maze[i][p]=='E'){
                    System.out.print('→');
                    System.out.print(' ');
                }
                else if( maze[i][p]=='L'){
                    System.out.print('←');
                    System.out.print(' ');
                }
                else if( maze[i][p]=='U'){
                    System.out.print('↑');
                    System.out.print(' ');
                }
                else if( maze[i][p]=='D'){
                    System.out.print('↓');
                    System.out.print(' ');
                }
                else if( maze[i][p]=='G'){
                    System.out.print(' ');
                    System.out.print(' ');
                }
                else if(maze[i][p]=='W'){
                    System.out.print(ANSI_RED+'■'+ANSI_RESET);
                    System.out.print(' ');
                }
                else if(maze[i][p]=='C'){
                    System.out.print(' ');
                    System.out.print(' ');
                }
                else if(i==0 && p ==0){
                    System.out.print('┌');
                    System.out.print('─');
                }
                else if(i==0 && p==maze[i].length-1){
                    System.out.print('┐');
                }
                else if(i==maze.length-1 && p ==0){
                    System.out.print('└');
                    System.out.print('─');
                }
                else if(i==maze.length-1  && p==maze[i].length-1){
                    System.out.print('┘');
                }
                else if(i==0 || i==maze.length-1){
                    System.out.print('─');
                    System.out.print('─');
                }
                else if(p==0 || p==maze[i].length-1){
                    System.out.print('│');
                    System.out.print(' ');
                }
                else{
                    System.out.print('·');
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    public static boolean argsNumCheck(String[] args){
        if(args.length != 1){
            if( args.length==0){
                UI.printErrors(6);
            }
            else{
                UI.printErrors(7);
            }
            return false;
        }
        return true;
    }

    public static boolean argExtensionCheck(String filename){
        if(filename.endsWith(".dat") || !filename.contains(".")) {
            return true;
        }
        UI.printErrors(5);
        return false;
    }
    public static boolean MazeCheck(File file, boolean emptyMaze, String filename)throws IOException{
        if(file.exists()) { // Checking if the file exists
            emptyMaze =!checkMaze(filename);
            if(!emptyMaze){
                UI.printErrors(1);
                return false;
            }
            return true;
        }
        else {
            UI.printErrors(4);
            return false;
        }
        
    }
}