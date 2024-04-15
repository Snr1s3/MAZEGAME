#!/bin/bash
#set -x
#Colors i variables
GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
BOLD='\033[1m'
NC='\033[0m' # No Color
i=0
#Functions
encript(){
    python3 mazes/encrypt.py 
    python3 tests/encrypt.py 
}
decript(){
    python3 mazes/decrypt.py 
    python3 tests/decrypt.py 
}
delete_files(){
    rm output.txt && cp tests/mazeDB.csv mazes/mazeDB.csv && rm tests/mazeDB.csv
    encript
}
check_result() {
    i=$((i+1)) # Increment the counter
    echo -n "${BLUE}Prova $i ${NC}"
    java MazeGame $2 $3 $4 > output.txt
    diff output.txt $1 > /dev/null
    if [ $? -eq 0 ]
    then
        echo  "${GREEN} passada${NC}"
    else
        echo  "${RED}\nDiferencies trobades${NC}"
        diff -y --color output.txt $1
        echo "${RED}Prova $i no passada. Aturant proves...${NC}"
        delete_files
        exit 1
    fi
}

check_resultV2() {
    i=$((i+1)) # Increment the counter
    echo -n "${BLUE}Prova $i ${NC}"
    java MazeGame $2 < $3 > output.txt
    diff output.txt $1 > /dev/null
    if [ $? -eq 0 ]
    then
        echo  "${GREEN} passada${NC}"
    else
        echo  "${RED}\nDiferencies trobades${NC}"
        diff -y --color output.txt $1
        echo "${RED}Prova $i no passada. Aturant proves...${NC}"
        delete_files
        exit 1
    fi
}

header(){
    decript
    echo  "${BOLD}${YELLOW}Joc De Proves${NC}"
    echo  "${BOLD}${YELLOW}----------------${NC}"
    cp mazes/mazeDB.csv tests/ &&  cp tests/test_mazeDB.csv mazes/mazeDB.csv
}
footer(){
    echo  "${BOLD}${YELLOW}Totes les proves han passat${NC}"
    delete_files
    exit 0
}
#Main
header

check_result tests/test_moltslaberints.out laberint05.dat laberint05.dat fitxernull

check_result tests/test_fitxerInexistent.out fitxernull

check_result tests/test_senselaberint.out

cp tests/laberint05.dat mazes/laberint05.dat
check_result tests/test_laberintbuit.out laberint05.dat
rm mazes/laberint05.dat 

cp tests/laberint06.dat mazes/laberint06.dat
check_result tests/test_laberintinacabable.out laberint06.dat
rm mazes/laberint06.dat

check_result tests/test_laberintextensionovalida.out mazeDB.csv

check_resultV2 tests/test_laberint01.out laberint01.dat tests/test_laberint01.in

check_resultV2 tests/test_laberint02.out laberint02.dat tests/test_laberint02.in

check_resultV2 tests/test_laberint03.out laberint03.dat tests/test_laberint03.in

check_resultV2 tests/test_laberint04.out laberint04.dat tests/test_laberint04.in

footer