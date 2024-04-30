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
        System.out.println(ANSI_CYAN + "Les opcions disponibles són:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "H: Mostra aquest text d'ajuda" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "L: gira a l'esquerra" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "R: gira a la dreta" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "F: mou una passa endavant" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "nF: mou n passes endavant" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Q: Sortir" + ANSI_RESET);
    }

    // Method to print the game header
    public static void header(){
        System.out.println(ANSI_BOLD + ANSI_BLUE+"Joc del laberint"+ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BLUE+"================"+ANSI_RESET);
        System.out.println(ANSI_CYAN + "H: mostra ajuda"+ ANSI_RESET);
    }

    // Method to show maze info
    public static void printMazeInfo(String mazeName, int attempts, boolean record, String[] recordString){ //Check if the file exists and show the  maze stats(name, tries, solved or not))
        System.out.println();
        System.out.println(ANSI_CYAN+"Laberint: "+ mazeName+ANSI_RESET);
        if(record){
            System.out.println(ANSI_CYAN+"Rècord actual: "+ recordString[2] +" en "+recordString[1]+" intents"+ANSI_RESET);
        }
        else{
            System.out.println(ANSI_CYAN+"Encara no resolt"+ANSI_RESET);
        }
        System.out.println();
        System.out.println(ANSI_CYAN+"Intents actuals: "+1+ANSI_RESET);
    }

    public static void userPrompt(String mazeName){
        System.out.println(ANSI_CYAN+"┌─("+mazeName+")"+ ANSI_RESET);
        System.out.println(ANSI_CYAN+"│"+ ANSI_RESET);
        System.out.print(ANSI_CYAN +"└─> "+ ANSI_RESET);
    }

    public static void printQ(){
        System.out.println(ANSI_YELLOW+"TENS POR?"+ANSI_RESET); 
    }

    public static void printWin(){
        System.out.println(ANSI_GREEN+"Aconseguit!"+ANSI_RESET);
    }
    public static void printMazeAndMove(int attempts, char[][] map){
        System.out.println(); 
        // Print the current number of attempts
        System.out.println("Intents actuals: "+  attempts);
            // Print the current state of the maze
        Maze.printMaze(map);
        System.out.println();
    }

    public static void printAttemptsRecord(int attempts){
        if(attempts == 1){
            System.out.println(ANSI_CYAN+"Has resolt el laberint en "+  attempts+" intent"+ANSI_RESET);
        }
        else{
            System.out.println(ANSI_CYAN+"Has resolt el laberint en "+  attempts+" intents"+ANSI_RESET);
        }
    }

    public static void printNewRecord(boolean record){
        if(record){
            System.out.print(ANSI_GREEN+"Nou rècord! Indica el teu nom:"+ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED+"No has superat el rècord. Potser la següent vegada."+ANSI_RESET);
        }
    }
    public static void printAllHeader(Maze laberint ,Record record, boolean hasRecord)throws IOException{
        header(); // Printing the header
        if(hasRecord){
            record.storeRecord(laberint.getMazeName());
            printMazeInfo(laberint.getMazeName(), 1,hasRecord, record.getRecord()); // Showing maze info
        }
        else{
            printMazeInfo(laberint.getMazeName(), 1,hasRecord, record.getRecord()); // Showing maze info
        } 
    }

    public static void printErrors(int numError){
        switch(numError){
            case 1 :
                System.out.println(ANSI_BOLD + ANSI_RED + "No hi ha cap laberint" + ANSI_RESET);
                break;
            case 2 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Moviment invàlid" + ANSI_RESET);
                break;
            case 3 :
                System.out.println(ANSI_BOLD + ANSI_RED + "No es pot arribar al final del laberint" + ANSI_RESET);
                break;
            case 4 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Laberint no existent" + ANSI_RESET);
                break;
            case 5 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Extensió del fitxer invàlida" + ANSI_RESET);
                break;
            case 6 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Cap argument especificat" + ANSI_RESET);
                break;
            case 7 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Masses arguments especificats" + ANSI_RESET);
                break;
            case 8 :
                System.out.println(ANSI_BOLD + ANSI_RED + "Cap moviment fet" + ANSI_RESET);
                break;
        }
    }
}