import java.io.*;
import java.util.*;
import IntList.IntList;

public class WsppSortedFirst {

    static boolean partOfWrord(char partOfLine) {
        return partOfLine == '\'' || Character.DASH_PUNCTUATION == Character.getType(partOfLine);
    }

    public static void main(String[] args) {
        String fileNameInput = args[0];
        String fileNameOutput = args[1];
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileNameInput), "UTF-8"));
                BufferedWriter outputFile = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(fileNameOutput), "UTF-8"));) {
            int position = 1;
            HashMap<String, int[]> output = new HashMap<>();
            String keys = "";
            int whatLine = 0;
            while (input.ready()) {
                whatLine = whatLine + 1;
                String line = input.readLine().toLowerCase() + " ";
                int i = 0;
                String word = "";
                while (i < line.length()) {
                    if (partOfWrord(line.charAt(i))) {
                        word += line.charAt(i);
                    } else {
                        if (word != "") {
                            if ((output.containsKey(word))) {
                                int[] listValue = output.get(word);
                                if (listValue.length == output.get(word)[2] + 4) {
                                    listValue = IntList.alloc(listValue);
                                }
                                listValue[2] = listValue[2] + 1;
                                if (whatLine != output.get(word)[0]) {
                                    listValue[0] = whatLine;
                                    listValue[1] = listValue[1] + 1;
                                    listValue[listValue[1] + 2] = position;
                                }
                                output.put(word, listValue);
                            } else {
                                int[] listValue = new int[10];
                                listValue[2] = 1;
                                listValue[3] = position;
                                listValue[0] = whatLine;
                                listValue[1] = 1;
                                output.put(word, listValue);
                                keys = keys + word + " ";
                            }
                            position = position + 1;
                        }
                        word = "";
                    }
                    i++;
                }
            }
            keys.strip();
            String[] listKeys = keys.split(" ");
            Arrays.sort(listKeys);
            if (keys.length() != 0) {
                for (int j = 0; j < listKeys.length; j++) {
                    outputFile.write(listKeys[j] + " ");
                    for (int i = 2; i < output.get(listKeys[j])[1] + 3; i++) {
                        outputFile.write(Integer.toString(output.get(listKeys[j])[i]));
                        if (i != output.get(listKeys[j])[1] + 2) {
                            outputFile.write(" ");
                        }
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
