import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
public class MazeUtilities {
    // ANSI escape code for red text
    public static final String ANSI_RED = "\u001B[31m";
    // ANSI escape code to reset text color
    public static final String ANSI_RESET = "\u001B[0m";

    // Method to trim the maze name
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
                Errors.printErrors(6);
            }
            else{
                Errors.printErrors(7);
            }
            return false;
        }
        return true;
    }

    public static boolean argExtensionCheck(String filename){
        if(filename.endsWith(".dat") || !filename.contains(".")) {
            return true;
        }
        Errors.printErrors(5);
        return false;
    }
    public static boolean MazeCheck(File file, boolean emptyMaze, String filename)throws IOException{
        if(file.exists()) { // Checking if the file exists
            emptyMaze =!MazeUtilities.checkMaze(filename);
            if(!emptyMaze){
                Errors.printErrors(1);
                return false;
            }
            return true;
        }
        else {
            Errors.printErrors(4);
            return false;
        }
        
    }
}