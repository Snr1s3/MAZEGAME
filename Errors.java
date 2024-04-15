public class Errors {
    // ANSI escape code for red text
    private static final String ANSI_RED = "\u001B[31m";
    // ANSI escape code to reset text color
    private static final String ANSI_RESET = "\u001B[0m";

    //Bold
    private static final String ANSI_BOLD = "\u001B[1m";


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