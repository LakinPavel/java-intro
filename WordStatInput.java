import java.io.*;
import java.util.*;

public class WordStatInput {

    public static void main(String[] args) {
        try {
            HashMap<String, Integer> output = new HashMap<>();
            String fileNameInput = args[0];
            String fileNameOutput = args[1];
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameInput), "UTF-8"));
            BufferedWriter outputFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileNameOutput), "UTf-8"));
            String keys = "";
            while (input.ready()) {
                String line = input.readLine().toLowerCase() + " ";
                int i = 0;
                String word = "";
                while (i < line.length()) {
                    if ((line.charAt(i) == '\'') || (Character.DASH_PUNCTUATION == Character.getType(line.charAt(i))) || (Character.isLetter(line.charAt(i)))) {
                        word += line.charAt(i);
                    } else {
                        if (word.length() != 0) {
                            if ((output.containsKey(word))) {
                                output.put(word, output.get(word) + 1);
                            } else {
                                output.put(word, 1);
                                keys = keys + word + " ";
                            }
                        }
                        word = "";
                    }
                    i++;
                }
            }
            keys.strip();
            String[] SpisokKeys = keys.split(" ");
            for (int j = 0; j < SpisokKeys.length; j++) {
                outputFile.write(SpisokKeys[j] + " " + Integer.toString(output.get(SpisokKeys[j])) + "\n");
            }
            input.close();
            outputFile.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error FNFE");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error UEE");
        } catch (IOException e) {
            System.err.println("Error IOE");
        }
    }
}





//Вывод ошибок и названия переменных
