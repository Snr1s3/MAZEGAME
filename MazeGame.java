import java.io.IOException;
import java.io.File;
public class MazeGame {
    public static void main(String[] args) throws IOException{
        int moveExitCode=0; // Variable to store the exit code of the move
        boolean hasRecord= false;
        boolean emptyMaze= false;
        if(!MazeUtilities.argsNumCheck(args)){
            return;
        }
        String filename = "mazes/"+args[0];
        if(!MazeUtilities.argExtensionCheck(filename)){
            return;
        }
        if(!filename.endsWith(".dat")) {
            filename+=".dat";
        }
        File file = new File(filename); // Creating a File object with the first command line argument
        if(!MazeUtilities.MazeCheck(file, emptyMaze, filename)){
            return;
        }

        Maze laberint = new Maze(filename); // Creating a Maze object with the file
        Maze mazeCanReachEnd = new Maze(filename);
        mazeCanReachEnd.setMaze();
        BackTracking backTracking = new BackTracking();
        int[] start = backTracking.startPosition(mazeCanReachEnd.getMazeMap());
        boolean canReach = backTracking.canFinish(mazeCanReachEnd.getMazeMap(), start[0], start[1]);                        
        mazeCanReachEnd = null;
        if(!canReach){
            Errors.printErrors(3);
            return;
        }
        Player player = Player.getPlayer(start); // Creating a new Player object
        Record record = new Record();
        if(record.checkRecord(laberint.getMazeName())){
           hasRecord=true;
        }
        UI.printAllHeader(laberint, record, hasRecord);
        
        laberint.setMaze(); // Setting up the maze
        player.setInitialPosi(laberint.getMazeMap());// Setting the initial position of the player
        laberint.getMaze(); // Getting the maze
        System.out.println();                        
        while(true){
            UI.userPrompt(laberint.getMazeName());
            String input=Entrada.readLine();
            if(!Input.invalidMoves(input)){
                input=input.toUpperCase();
                input =Input.playerInput(input); // Reading user input
                if(Input.containsQ(input) || Input.containsH(input) ){
                    if(Input.containsH(input)){
                        UI.showHelp();
                    }else{
                        UI.printQ();
                        break; 
                    }
                    
                }        
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
            UI.printMazeAndMove(player.getIntents(),laberint.getMazeMap());
            //System.out.println(input);// Un comment the line to see the player input
            // If the exit code of the move is 3, print a success message and break the outer loop
            if( moveExitCode == 3){
                record.newRecord(hasRecord, player.getIntents(), laberint.getMazeName());
                break;
            }
        }
            
    }
}