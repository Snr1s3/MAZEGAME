#/bin/bash
#set -x
#Functions
encript(){
    python3 mazes/encrypt.py
    python3 tests/encrypt.py 
}
decript(){
    python3 mazes/decrypt.py 
    python3 tests/decrypt.py 
}
#Main
decript
java MazeGame $1 
encript
