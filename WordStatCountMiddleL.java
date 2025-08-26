import java.io.*;
import java.util.*;

public class WordStatCountMiddleL {

    static boolean partOfWrord(char partOfLine) {
        if ((partOfLine == '\'') || (Character.DASH_PUNCTUATION == Character.getType(partOfLine))
                || (Character.isLetter(partOfLine))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String fileNameInput = args[0];
        String fileNameOutput = args[1];
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileNameInput), "UTF-8"));
                BufferedWriter outputFile = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(fileNameOutput), "UTF-8"));) {
            HashMap<String, Integer> output = new HashMap<>();
            String keys = "";
            while (input.ready()) {
                String line = input.readLine().toLowerCase() + " ";
                int i = 0;
                String word = "";
                while (i < line.length()) {
                    if (partOfWrord(line.charAt(i))) {
                        word += line.charAt(i);
                    } else {
                        if (word.length() > 4) {
                            String sliceWord = word.substring(2, word.length() - 2);
                            if ((output.containsKey(sliceWord))) {
                                output.put(sliceWord, output.get(sliceWord) + 1);
                            } else {
                                output.put(sliceWord, 1);
                                keys = keys + sliceWord + " ";
                            }
                        }
                        word = "";
                    }
                    i++;
                }
            }
            keys.strip();
            String[] listKeys = keys.split(" ");
            Arrays.sort(listKeys, 0, listKeys.length, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return output.get(s1) - output.get(s2);
                }
            });
            if (keys.length() != 0) {
                for (int j = 0; j < listKeys.length; j++) {
                    outputFile.write(listKeys[j] + " " + Integer.toString(output.get(listKeys[j])) + "\n");
                }


            }
        } catch (FileNotFoundException e) {
            System.err.println("Error File Not found" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error Unsupported Encoding Exception" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error IOE" + e.getMessage());
        }
    }
}
