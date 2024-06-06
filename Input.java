public class Input {
    // Method to process the player's input
    public static String playerInput(String input){ //Transforms the player input to a String with the inputs that we can use
        String movementPlayer="";
        String movementPlayerFiltered ="";
        int letterPosition =0;
        while (letterPosition < input.length()) {
            if (Character.isDigit(input.charAt(letterPosition))) {
                int repeat = Character.getNumericValue(input.charAt(letterPosition));
                letterPosition++; // Move to the next character
                if (letterPosition < input.length()) {
                    for (int j = 0; j < repeat; j++) {
                        movementPlayer +=input.charAt(letterPosition);
                    }
                }
            } else {
                movementPlayer+=input.charAt(letterPosition);
            }
            letterPosition++;
        }
        for(int i= 0; i< movementPlayer.length(); i++){
            switch (movementPlayer.charAt(i)) {
                case 'H':
                case 'L':
                case 'R':
                case 'F':
                case 'Q':
                    movementPlayerFiltered += movementPlayer.charAt(i);
                    break;
                default:
            }
        }
        return movementPlayerFiltered;
    }

    // Method to check if the input contains 'Q' or 'H'
    public static int containsQorH(String input){
        for(int i= 0; i< input.length(); i++){    
            switch (input.charAt(i)) {
                case 'Q':
                    return 1;
                case 'H':
                    return 2;
                default:
            }            
        }
        return 0;
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
            if(!Character.isDigit(input.charAt(i))){
                switch (input.charAt(i)) {
                    case 'H':
                    case 'Q':
                    case 'R':
                    case 'L':
                    case 'F':
                        break;              
                    default:
                        return true;
                }    
            }  
        }
        return false;
    }

    public static boolean invalidMoves(String input){
        if(input.isBlank() || digitsCheck(input.toUpperCase())|| inavlidChar(input.toUpperCase())){
            if(digitsCheck(input.toUpperCase()) ||inavlidChar(input.toUpperCase()) ){
                UI.printErrors(2);
            }
            else{
                UI.printErrors(8);
            }
            return true;
        }
        return false;
    }
    
}