import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;

public  class Maze{
    private char[][] maze; // 2D array to represent the maze
    private String mazeName; // Name of the maze
    private String mazeFileName; // File name of the maze
    
    // Constructor for the Maze class
    // It takes the name of the maze file as an argument
    public Maze(String mazeName){ 
        mazeFileName = mazeName; // Store the file name
        // Trim the maze name and store it
        this.mazeName = MazeUtilities.trimMazeName(mazeName);
    }

    // Method to print the maze
    public void getMaze(){ 
        MazeUtilities.printMaze(maze);
    }

    // Method to read the maze from the file and store it in the 2D array
    public void setMaze()throws IOException{ 
        this.maze = MazeUtilities.readMaze(mazeFileName);
    }

    // Getter method for the maze 2D array
    public char[][] getMazeMap(){ 
        return maze;
    }
    // Getter method for the maze name
    public String getMazeName(){ 
        return mazeName;
    }
}