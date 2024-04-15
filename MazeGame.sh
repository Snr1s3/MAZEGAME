#/bin/bash
#set -x
#Functions
encript(){
    python3 mazes/encrypt.py 
}
decript(){
    python3 mazes/decrypt.py 
}
#Main
decript
java MazeGame $1 
encript
