public class Input {
    // Method to process the player's input
    public static String playerInput(String input){ //Transforms the player input to a String with the inputs that we can use
        String movementPlayer="";
        String movementPlayerFiltered ="";
        int letterPosition =0;
        while (letterPosition < input.length()) {
            char currentChar = input.charAt(letterPosition);
            if (Character.isDigit(currentChar)) {
                int repeat = Character.getNumericValue(currentChar);
                letterPosition++; // Move to the next character
                if (letterPosition < input.length()) {
                    char nextChar = input.charAt(letterPosition);
                    for (int j = 0; j < repeat; j++) {
                        movementPlayer +=nextChar;
                    }
                }
            } else {
                movementPlayer+=currentChar;
            }
            letterPosition++;
        }
        for(int i= 0; i< movementPlayer.length(); i++){
            char currentChar = movementPlayer.charAt(i);
            switch (currentChar) {
                case 'H':
                    movementPlayerFiltered += currentChar;
                    break;
                case 'L':
                    movementPlayerFiltered += currentChar;
                    break;
                case 'R':
                    movementPlayerFiltered += currentChar;
                    break;
                case 'F':
                    movementPlayerFiltered += currentChar;
                    break;
                case 'Q':
                    movementPlayerFiltered += currentChar;
                    break;
                default:
            }
        }
        return movementPlayerFiltered;
    }

    // Method to check if the input contains 'Q'
    public static boolean containsQ(String input){
        boolean contains = false;
        for(int i= 0; i< input.length(); i++){            
            char currentChar = input.charAt(i);
            switch (currentChar) {
                case 'Q':
                    contains = true;
                    break;
                default:
            }            
        }
        return contains;
    }

    // Method to check if the input contains 'H'
    public static boolean containsH(String input){
        boolean contains = false;
        for(int i= 0; i< input.length(); i++){            
            char currentChar = input.charAt(i);
            switch (currentChar) {
                case 'H':
                    contains = true;
                    break;
                default:
            }            
        }
        return contains;
    }
    public static boolean digitsCheck(String input){
        boolean contains = false;
        if(Character.isDigit(input.charAt(input.length()-1))){
            return true;
        }
        for(int i = 0; i<input.length()-1;i++){
            if(Character.isDigit(input.charAt(i)) && Character.isDigit(input.charAt(i+1))){
                contains = true;
            }
        } 
        return contains;
    }

    public static boolean inavlidChar(String input){
        for(int i= 0; i< input.length(); i++){            
            char currentChar = input.charAt(i);
            if(!Character.isDigit(currentChar)){
                switch (currentChar) {
                    case 'H':
                        break;
                    case 'Q':
                        break;
                    case 'R':
                        break;
                    case 'L':
                        break;
                    case 'F':
                        break;              
                    default:
                        return true;
                }    
            }  
        }
        return false;
    }
    
}