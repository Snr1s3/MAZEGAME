import java.io.IOException;
import java.io.File;
public class UI {
    // ANSI escape code to reset text color
    public static final String ANSI_RESET = "\u001B[0m";
    //More colors
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RED = "\u001B[31m";
    //Bold
    public static final String ANSI_BOLD = "\u001B[1m";
    

    // Method to print the help menu
    public static void showHelp(){
        System.out.println();
        System.out.println(ANSI_CYAN + "Available options are:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "H: Show this help text" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "L: Turn left" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "R: Turn right" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "F: Move one step forward" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "nL: Turn n times left" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "nR: Turn n tiems right" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "nF: Move n steps forward" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Q: Quit" + ANSI_RESET);
    }

    // Method to print the game header
    public static void header(){
        System.out.println(ANSI_BOLD + ANSI_BLUE+"THE MAZE GAME"+ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BLUE+"============="+ANSI_RESET);
        System.out.println(ANSI_CYAN + "H: Show this help text"+ ANSI_RESET);
    }

    // Method to show maze info
    public static void printMazeInfo(String mazeName, int attempts, boolean record, String[] recordString){ //Check if the file exists and show the  maze stats(name, tries, solved or not))
        System.out.println();
        System.out.println(ANSI_CYAN+"Maze: "+ mazeName+ANSI_RESET);
        if(record){
            System.out.println(ANSI_CYAN+"Actual record: "+ recordString[2] +" in "+recordString[1]+" intents"+ANSI_RESET);
        }
        else{
            System.out.println(ANSI_CYAN+"Record not found"+ANSI_RESET);
        }
        System.out.println();
        System.out.println(ANSI_CYAN+"Actual attempt 1"+ANSI_RESET);
    }

    public static void userPrompt(String mazeName){
        System.out.println(ANSI_CYAN+"┌─("+mazeName+")"+ ANSI_RESET);
        System.out.println(ANSI_CYAN+"│"+ ANSI_RESET);
        System.out.print(ANSI_CYAN +"└─> "+ ANSI_RESET);
    }

    public static void printQ(){
        System.out.println(ANSI_YELLOW+"ARE YOU SCARED?"+ANSI_RESET); 
    }

    public static void printWin(){
        System.out.println(ANSI_GREEN+"You win!!"+ANSI_RESET);
    }
    public static void printMazeAndMove(int attempts, char[][] map){
        System.out.println(); 
        // Print the current number of attempts
        System.out.println("Actual attempts: "+  attempts);
            // Print the current state of the maze
        UI.printMaze(map);
        System.out.println();
    }

    public static void printAttemptsRecord(int attempts){
        if(attempts == 1){
            System.out.println(ANSI_CYAN+"Maze finished in "+  attempts+" attempt"+ANSI_RESET);
        }
        else{
            System.out.println(ANSI_CYAN+"Maze finished in "+  attempts+" attempts"+ANSI_RESET);
        }
    }

    public static void printNewRecord(boolean record){
        if(record){
            System.out.print(ANSI_GREEN+"New record! Enter your name: "+ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED+"You haven't broken the record. Maybe next time."+ANSI_RESET);
        }
    }
    public static void printAllHeader(Maze laberint ,Record record, boolean hasRecord)throws IOException{
        header(); // Printing the header
        if(hasRecord){
            printMazeInfo(laberint.getMazeName(), 1,hasRecord, record.getRecord()); // Showing maze info
        }
        else{
            printMazeInfo(laberint.getMazeName(), 1,hasRecord, record.getRecord()); // Showing maze info
        } 
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

    public static void printErrors(int numError){
        switch(numError){
            case 1 :
                System.out.println(ANSI_BOLD + ANSI_RED + "No maze found" + ANSI_RESET);
                break;
            case 2 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Invalid move" + ANSI_RESET);
                break;
            case 3 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Can't finish the maze" + ANSI_RESET);
                break;
            case 4 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Maze not found" + ANSI_RESET);
                break;
            case 5 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Invalid file" + ANSI_RESET);
                break;
            case 6 :
                System.out.println(ANSI_BOLD + ANSI_RED + "No argument found" + ANSI_RESET);
                break;
            case 7 :
                System.out.println(ANSI_BOLD + ANSI_RED + "To much arguments" + ANSI_RESET);
                break;
            case 8 :
                System.out.println(ANSI_BOLD + ANSI_RED + "No movement done" + ANSI_RESET);
                break;
        }
    }
}