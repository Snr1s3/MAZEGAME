import java.io.IOException;
import java.io.File;
import java.sql.SQLException;

public class MazeGame {
    public static void main(String[] args) throws IOException{
        int moveExitCode=0; // Variable to store the exit code of the move
        boolean hasRecord= false;
        boolean emptyMaze= false;
        try{
            DataBase.connecta();
            DataBase.createTableMazes();
            DataBase.createTablePlayers();
        }catch(SQLException e){
            System.out.println(e);
        }
        if(!Maze.argsNumCheck(args)){
            return;
        }
        String filename = "mazes/"+args[0];
        if(!Maze.argExtensionCheck(filename)){
            return;
        }
        if(!filename.endsWith(".dat")) {
            filename+=".dat";
        }
        File file = new File(filename); // Creating a File object with the first command line argument
        if(!Maze.MazeCheck(file, emptyMaze, filename)){
            return;
        }

        Maze laberint = new Maze(filename); // Creating a Maze object with the file
        Maze mazeCanReachEnd = new Maze(filename);
        mazeCanReachEnd.setMaze();
        
        int[] start = mazeCanReachEnd.startPosition(mazeCanReachEnd.getMazeMap());
        if(!mazeCanReachEnd.canFinish(mazeCanReachEnd.getMazeMap(), start[0], start[1])){
            UI.printErrors(3);
            return;
        }                     
        mazeCanReachEnd = null;
        
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
            String input=LineReader.newLine();
            if(!input.isBlank()){
                if(!Input.invalidMoves(input)){
                    input=input.toUpperCase();
                    input =Input.playerInput(input); // Reading user input
                    if(Input.containsQorH(input) == 1){
                        UI.printQ();
                        break; 
                    }  
                    if(Input.containsQorH(input) == 2){
                        UI.showHelp();
                    }         
                    // Otherwise, loop through each character in the user's input
                    else if(Input.containsQorH(input) == 0){
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
            UI.printMazeAndMove(player.getIntents(),laberint.getMazeMap());
            //System.out.println(input);// Un comment the line to see the player input
            // If the exit code of the move is 3, print a success message and break the outer loop
            if( moveExitCode == 3){
                record.newRecord(hasRecord, player.getIntents(), laberint.getMazeName());
                break;
            }
        }  
        try{
            DataBase.desconnecta();
        }catch(SQLException e){
            System.out.println(e);
        }  
    }
}