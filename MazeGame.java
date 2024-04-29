import java.io.IOException;
import java.io.File;
public class MazeGame {
    public static void main(String[] args) throws IOException{
        int moveExitCode=0; // Variable to store the exit code of the move
        boolean hasRecord;
        boolean emptyMaze;
        if(!argsNumCheck(args)){
            return;
        }
        String filename = "mazes/"+args[0];
        if(!argExtensionCheck(filename)){
            return;
        }
        if(filename.endsWith(".dat") || !filename.contains(".")) {
            if(!filename.endsWith(".dat")) {
                filename+=".dat";
            }
            File file = new File(filename); // Creating a File object with the first command line argument
            if (file.exists()) { // Checking if the file exists
                emptyMaze =!MazeUtilities.checkMaze(filename);
                if(!emptyMaze){
                    Errors.printErrors(1);
                }
                else{
                    Maze laberint = new Maze(filename); // Creating a Maze object with the file
                    Maze mazeCanReachEnd = new Maze(filename);
                    mazeCanReachEnd.setMaze();
                    BackTracking backTracking = new BackTracking();
                    int[] start = backTracking.startPosition(mazeCanReachEnd.getMazeMap());
                    boolean canReach = backTracking.canFinish(mazeCanReachEnd.getMazeMap(), start[0], start[1]);                        
                    mazeCanReachEnd = null;
                    if(canReach){
                        Player player = Player.getPlayer(start); // Creating a new Player object
                        Record record = new Record();
                        UI.header(); // Printing the header
                        if(record.checkRecord(laberint.getMazeName())){
                            hasRecord=true;
                            record.storeRecord(laberint.getMazeName());
                            UI.printMazeInfo(laberint.getMazeName(), 1,true, record.getRecord()); // Showing maze info
                        }
                        else{
                            hasRecord=false;
                            UI.printMazeInfo(laberint.getMazeName(), 1,false, record.getRecord()); // Showing maze info
                        } 
                        laberint.setMaze(); // Setting up the maze
                        player.setInitialPosi(laberint.getMazeMap());// Setting the initial position of the player
                        laberint.getMaze(); // Getting the maze
                        System.out.println();                        
                        while(true){
                            UI.userPrompt(laberint.getMazeName());
                            String input=Entrada.readLine();
                            if(input.isBlank()){
                                Errors.printErrors(8);
                            }
                            else{
                                input=input.toUpperCase();
                                if(Input.digitsCheck(input) ){
                                    Errors.printErrors(2);
                                }
                                else{
                                    if(Input.inavlidChar(input) ){
                                        Errors.printErrors(2);
                                    }
                                    else{
                                        input =Input.playerInput(input); // Reading user input
                                        if(Input.containsQ(input)){ // If the input contains 'Q'
                                            UI.printQ();// Print a message and presumably exit the game
                                            break;
                                        }                                       
                                        // Check if the user's input contains 'H'
                                        if(Input.containsH(input)){
                                            // If it does, show the help menu
                                            UI.showHelp();
                                        }
                                        else{
                                            // Otherwise, loop through each character in the user's input
                                            for(int i =0; i<input.length();i++){
                                                // Turn the player left based on the current character
                                                player.turnLeft(input.charAt(i),laberint.getMazeMap());

                                                // Turn the player right based on the current character
                                                player.turnRight(input.charAt(i),laberint.getMazeMap());
                                                // Move the player forward based on the current character
                                                // and store the exit code of the move
                                                moveExitCode = player.moveForward(input.charAt(i),laberint.getMazeMap());
                                                /*Exit Code Meaning for moveExitCode
                                                1-Crash in to a wall
                                                2-Continue
                                                3-Exit Reached*/
                                                // If the exit code of the move is 1, break the loop
                                                if(moveExitCode== 1){
                                                    player.increaseAttempts(); // Incrementing the attempts of the player
                                                    break;
                                                }
                                                // If the exit code of the move is 3, print "Aconseguit!" and break the loop
                                                else if( moveExitCode == 3){
                                                    UI.printWin();
                                                    break;
                                                }
                                            }        
                                        }
                                    }                                
                                }   
                            }
                            UI.printMazeAndMove(player.getIntents(),laberint.getMazeMap());
                            //System.out.println(input);// Un comment the line to see the player input
                            // If the exit code of the move is 3, print a success message and break the outer loop
                            if( moveExitCode == 3){
                                record.newRecord(hasRecord, player.getIntents(), laberint.getMazeName());
                                break;
                            }
                        }
                    }
                    else{
                        Errors.printErrors(3);
                    }
                }
                // If the file does not exist, print "Laberint no existent"
            } else {
                Errors.printErrors(4);
            }
        }
        else{
            Errors.printErrors(5);
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
}