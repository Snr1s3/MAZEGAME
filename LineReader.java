import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class LineReader {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String newLine() {
        try {
            String line = reader.readLine();
            if (line == null) {
                throw new RuntimeException("Line is null");
            }
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}