public  class Player{
    // Singleton instance of Player
    private  static Player player = null;
    // Current position of the player
    private  static int[] playerPosi= new int[2];
    // Initial position of the player
    private  int[] initialPlayerPosi= new int[2];
    // Number of attempts made by the player
    private  int attempts = 1;
    // Current direction of the player
    private  String direction;
    private  String startDirection;

    // Constructor
    private Player(){}
    
    // Method to get the singleton instance of Player
    public  static Player getPlayer(int[] nPlayerPosi){ //? Com getInstancia
        if(player== null){
            player = new Player();
            playerPosi = nPlayerPosi;
        }
        return player;
    }

    // Method to get the number of attempts made by the player
    public  int getIntents(){
        if(player == null) player = new Player();
        return attempts;
    }

    // Method to get the current position of the player
    public  int[] getPosicio(){
        return playerPosi;
    }

    // Method to increment the number of attempts made by the player
    public void increaseAttempts() {
        attempts++;
    }

    // Method to set the initial position of the player
    public  void setInitialPosi(char[][] maze){
        for(int i = 0; i< maze.length; i++){            
            for(int p =0;p<maze[i].length;p++){
                if(maze[i][p]=='E'){
                    playerPosi[0]=i;
                    playerPosi[1]=p;
                    initialPlayerPosi[0]=i;
                    initialPlayerPosi[1]=p;
                    if(p==0){
                        direction = "right";
                        startDirection = "right";
                        maze[i][p]='R';
                    }
                    else if(p==maze.length){
                        direction = "left";
                        startDirection = "left";
                        maze[i][p]='L';
                    }
                    else if(i==0){
                        direction ="down";
                        startDirection="down";
                        maze[i][p]='D';
                    }
                    else if(i==maze.length-1){
                        direction = "up";
                        startDirection="up";
                        maze[i][p]='U';
                    }

                }
            }
        }
    }

    // Method to turn the player to the left
    public  void turnLeft(char input, char[][] maze){
        if(input == 'L'){
            if(direction.equals("right")){
                direction = "up";
                maze[playerPosi[0]][playerPosi[1]] = 'U';
            }
            else if(direction.equals("left")){
                direction = "down";
                maze[playerPosi[0]][playerPosi[1]] = 'D';
            }
            else if(direction.equals("up")){
                direction ="left";
                maze[playerPosi[0]][playerPosi[1]] = 'L';
            }
            else if(direction.equals("down")){
                direction = "right";
                maze[playerPosi[0]][playerPosi[1]] = 'R';
            }
        }
    }

    // Method to turn the player to the right
    public  void turnRight(char input, char[][] maze){
        if(input == 'R'){
            if(direction.equals("right")){
                direction = "down";
                maze[playerPosi[0]][playerPosi[1]] = 'D';
            }
            else if(direction.equals("left")){
                direction = "up";
                maze[playerPosi[0]][playerPosi[1]] = 'U';
            }
            else if(direction.equals("up")){
                direction ="right";
                maze[playerPosi[0]][playerPosi[1]] = 'R';
            }
            else if(direction.equals("down")){
                direction = "left";
                maze[playerPosi[0]][playerPosi[1]] = 'L';
            }
        }
    }

    // Method to move the player forward
    public  int moveForward(char input, char[][] maze){
        int[] playerPosiBKP= playerPosi;
        if(input == 'F'){
            if(direction.equals("left") && playerPosiBKP[1] > 0){
                playerPosiBKP[1] = playerPosiBKP[1] - 1;
            }
            else if(direction.equals("right") && playerPosiBKP[1] < maze[0].length - 1){
                playerPosiBKP[1] = playerPosiBKP[1] + 1;
            }
            else if(direction.equals("up") && playerPosiBKP[0] > 0){
                playerPosiBKP[0] = playerPosiBKP[0] - 1;
            }
            else if(direction.equals("down") && playerPosiBKP[0] < maze.length - 1){
                playerPosiBKP[0] = playerPosiBKP[0] + 1;
            }

            char nextChar = maze[playerPosiBKP[0]][playerPosiBKP[1]];
            if(playerPosiBKP[0]==initialPlayerPosi[0] && playerPosiBKP[1]==initialPlayerPosi[1] && isOtherDirection()){
               playerPosi[0]=initialPlayerPosi[0];
               playerPosi[1]=initialPlayerPosi[1];
               direction = startDirection;
               maze[initialPlayerPosi[0]][initialPlayerPosi[1]]=deadChar();
               System.out.println("Xoc!");
               return 1;
               
            }
            else if(nextChar == 'X' || nextChar == 'W'){
               maze[playerPosiBKP[0]][playerPosiBKP[1]]='W';
               if(direction.equals("right")){
                    maze[playerPosi[0]][playerPosi[1]- 1] = 'C';
                }
                else if(direction.equals("left")){
                    maze[playerPosi[0]][playerPosi[1] + 1] = 'C';
                }
                else if(direction.equals("up")){
                    maze[playerPosi[0] + 1][playerPosi[1]] = 'C';
                }
                else if(direction.equals("down")){
                    maze[playerPosi[0] - 1][playerPosi[1]] = 'C';
                }
               playerPosi[0]=initialPlayerPosi[0];
               playerPosi[1]=initialPlayerPosi[1];
               direction = startDirection;
               maze[initialPlayerPosi[0]][initialPlayerPosi[1]]=deadChar();
               System.out.println("Xoc!");
               return 1;
            }
            else if(nextChar == 'G'){
                playerPosi[0]=playerPosiBKP[0];
                playerPosi[1]=playerPosiBKP[1];
                if(direction.equals("right")){
                    maze[playerPosi[0]][playerPosi[1]- 1] = 'C';
                    maze[playerPosi[0]][playerPosi[1]]= 'R';
                }
                else if(direction.equals("left")){
                    maze[playerPosi[0]][playerPosi[1] + 1] = 'C';
                    maze[playerPosi[0]][playerPosi[1]]='L';
                }
                else if(direction.equals("up")){
                    maze[playerPosi[0] + 1][playerPosi[1]] = 'C';
                    maze[playerPosi[0]][playerPosi[1]]= 'U';
                }
                else if(direction.equals("down")){
                    maze[playerPosi[0] - 1][playerPosi[1]] = 'C';
                    maze[playerPosi[0]][playerPosi[1]]= 'D';
                }
                return 3;
            }
            else if(nextChar != 'W' && nextChar != 'X'){
                playerPosi[0]=playerPosiBKP[0];
                playerPosi[1]=playerPosiBKP[1];
                if(direction.equals("right")){
                    maze[playerPosi[0]][playerPosi[1]- 1] = 'C';
                    maze[playerPosi[0]][playerPosi[1]]= 'R';
                }
                else if(direction.equals("left")){
                    maze[playerPosi[0]][playerPosi[1] + 1] = 'C';
                    maze[playerPosi[0]][playerPosi[1]]='L';
                }
                else if(direction.equals("up")){
                    maze[playerPosi[0] + 1][playerPosi[1]] = 'C';
                    maze[playerPosi[0]][playerPosi[1]]= 'U';
                }
                else if(direction.equals("down")){
                    maze[playerPosi[0] - 1][playerPosi[1]] = 'C';
                    maze[playerPosi[0]][playerPosi[1]]= 'D';
                }
                return 2;
            }
        }
        return 4;
    }
    public boolean isOtherDirection(){
        if(direction.equals("up") && startDirection.equals("down")){
            return true;
        }
        else if(direction.equals("down") && startDirection.equals("up")){
            return true;
        }
        else if(direction.equals("right") && startDirection.equals("left")){
            return true;
        }
        else if(direction.equals("left") && startDirection.equals("right")){
            return true;
        }
        return false;
    }

    public char deadChar(){
        if(direction.equals("up")){
            return 'U';
        }
        else if(direction.equals("down")){
            return 'D';
        }
        else if(direction.equals("right")){
            return 'R';
        }
        else if(direction.equals("left")){
            return 'L';
        }
        return 'L';
    }
}
