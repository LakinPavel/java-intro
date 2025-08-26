import java.io.*;
import java.util.*;
import IntList.IntList;


public class Wspp {

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
            int position = 1;
            // IntList intList = new IntList();
            HashMap<String , int[]> output = new HashMap<>();
            String keys = "";
            while (input.ready()) {
                String line = input.readLine().toLowerCase() + " ";
                int i = 0;
                String word = "";
                while (i < line.length()) {
                    if (partOfWrord(line.charAt(i))) {
                        word += line.charAt(i);
                    } else {
                        // if (word.length() > 4) {
                        // String sliceWord = word.substring(2, word.length() - 2);
                        if (word != ""){
                            // System.err.println(word);
                            if ((output.containsKey(word))) {
                                int[] listValue = output.get(word);
                                if (listValue.length == output.get(word)[0] + 1){
                                    listValue = IntList.alloc(listValue);               
                                }
                                listValue[0] = listValue[0] + 1;
                                listValue[listValue[0]] = position;    
                                output.put(word, listValue);
                                // position = position + 1;
                            } else {
                                int[] listValue = new int[10];
                                listValue[0] = 1;
                                listValue[1] = position;
                                output.put(word, listValue);
                                keys = keys + word + " ";
                                // position = position + 1;
                                }
                            position = position + 1;
                            }
                        // }
                        word = "";
                    }
                    i++;
                }
            }
            keys.strip();
            String[] listKeys = keys.split(" ");
            // Arrays.sort(listKeys, 0, listKeys.length, new Comparator<String>() {
            //     public int compare(String s1, String s2) {
            //         return output.get(s1)[0] - output.get(s2)[0];
            //     }
            // });
            if (keys.length() != 0) {
                for (int j = 0; j < listKeys.length; j++) {
                    // System.err.println(output);
                    // System.err.println(output.get(listKeys[j]));
                    // outputFile.write(listKeys[j] + " " + Arrays.toString(output.get(listKeys[j])) + "\n");
                    outputFile.write(listKeys[j] + " ");
                    for (int i = 0; i < output.get(listKeys[j])[0] + 1; i ++){
                        outputFile.write(Integer.toString(output.get(listKeys[j])[i]));
                        if (i != output.get(listKeys[j])[0]){
                            outputFile.write(" ");
                        }
                        // System.err.println("pochemu" + " " + listKeys[j]);
                    }
                    outputFile.write("\n");
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

