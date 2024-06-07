import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.sql.SQLException;

public class Record {
    private String[] recordInfo = new String[3];

    public boolean checkRecord(String mazeName) throws IOException{
        try{
            String[] parameters = DataBase.getMazeRecord(mazeName);
            if(parameters != null && !isArrayEmpty(parameters)) {
                recordInfo[0] = parameters[0];
                recordInfo[1] = parameters[1];
                recordInfo[2] = parameters[2];
                return true;
            }
            return false;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public void newRecord(boolean record, int attempts, String mazeName)throws IOException{
        UI.printAttemptsRecord(attempts);
        System.out.println();
        if(!record || attempts< Integer.parseInt(recordInfo[1])){
            UI.printNewRecord(true);
            String name=LineReader.newLine();
            while(name.isBlank()){
                System.out.println();
                UI.printNewRecord(true);
                name=LineReader.newLine();
            }
            name=name.trim();
            try{
                DataBase.insertRecord(mazeName, attempts, name);
            }catch(SQLException e){
                System.out.println(e);
            }
        }
        else{
            UI.printNewRecord(false);
        }
    }

    public String[] getRecord(){
        return recordInfo;
    }

    public boolean isArrayEmpty(String[] array) {
        for (String element : array) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }
}