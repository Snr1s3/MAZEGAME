import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;

public class Record {
    private String[] recordInfo = new String[3];

    public boolean checkRecord(String mazeName) throws IOException{
        FileReader fR = new FileReader("mazes/mazeDB.csv");
        BufferedReader reader = new BufferedReader(fR);
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parameters=line.split(",");
            if(parameters[0].equals(mazeName)){
                recordInfo[0] = parameters[0];
                recordInfo[1] = parameters[1];
                recordInfo[2] = parameters[2];
                fR.close();
                reader.close();
                return true;
            }
        }
        fR.close();
        reader.close();
        return false;
    }

    public void newRecord(boolean record, int attempts, String mazeName)throws IOException{
        UI.printAttemptsRecord(attempts);
        System.out.println();
        if(!record || attempts< Integer.parseInt(recordInfo[1])){
            FileWriter writer = new FileWriter("mazes/mazeDB.csv", true);
            writer.write(System.lineSeparator()); // Add a new line
            UI.printNewRecord(true);
            String name=LineReader.newLine();
            while(name.isBlank()){
                System.out.println();
                UI.printNewRecord(true);
                name=LineReader.newLine();
            }
            name=name.trim();
            String newLine=mazeName+","+attempts+","+name;
            writer.write(newLine); // Write the new line
            writer.close();
        }
        else{
            UI.printNewRecord(false);
        }
    }

    public String[] getRecord(){
        return recordInfo;
    }
}