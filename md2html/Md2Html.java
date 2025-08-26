package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

public class Md2Html {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        String fileNameInput = args[0];
        String fileNameOutput = args[1];
        BufferedReader inputFile = new BufferedReader(new FileReader(fileNameInput, StandardCharsets.UTF_8));
        BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileNameOutput, StandardCharsets.UTF_8));
        StringBuilder line = new StringBuilder();
        int flag = 1;
        int counter = 2;
        while (inputFile.ready()) {
            String curLine = inputFile.readLine();
            if (!curLine.isEmpty()) {
                if (flag == 0) {
                    line.append("\n");
                }
                line.append(curLine);
                flag = 0;
                counter = 0;
            } else {
                counter += 1;
                if (counter == 1) {
                    outputFile.write(html(line.toString()));
                    line = new StringBuilder();
                }
                flag = 1;
            }
        }
        outputFile.write(html(line.toString()));
        inputFile.close();
        outputFile.close();
    }

    public static boolean notmark(int i, String l) {
        return ((i - 1 >= 0 && l.charAt(i - 1) != '\\') | i == 0);
    }

    public static String html(String s) {
        HashMap<Character, String> codes = new HashMap<>();
        codes.put('<', "&lt;");
        codes.put('>', "&gt;");
        codes.put('&', "&amp;");
        codes.put('\\', "");
        int deep = 0;
        StringBuilder[] linesDeep = new StringBuilder[10];
        for (int q = 0; q < 10; q++) {
            linesDeep[q] = new StringBuilder();
        }
        String[] marksDeep = new String[10];
        int index = 0;
        int cnt = 0;
        int flag = 0;
        while (index < s.length() && s.charAt(index) == '#') {
            index += 1;
            cnt += 1;
        }
        if (index > 0 && index < s.length() && Character.isWhitespace(s.charAt(index))) {
            linesDeep[deep].append("<h" + Integer.toString(cnt) + ">");
            flag = 1;
            index += 1;
        } else {
            linesDeep[deep].append("<p>").append("#".repeat(cnt));
        }

        while (index < s.length()) {
            if (Objects.equals(marksDeep[deep], "```")) {
                if (notmark(index, s) && index + 2 < s.length() && s.charAt(index) == '`' && s.charAt(index + 1) == '`'
                        && s.charAt(index + 2) == '`') {
                    linesDeep[deep - 1].append("<pre>" + linesDeep[deep].toString() + "</pre>");
                    linesDeep[deep] = new StringBuilder();
                    deep -= 1;
                    index += 3;
                } else {
                    if (codes.containsKey(s.charAt(index))) {
                        linesDeep[deep].append(String.valueOf(codes.get(s.charAt(index))));
                    } else {
                        linesDeep[deep].append(String.valueOf(s.charAt(index)));
                    }
                    index += 1;
                }
            } else {
                if (notmark(index, s) && s.charAt(index) == '*') {
                    if (index + 1 < s.length() && s.charAt(index + 1) != '*') {
                        if (marksDeep[deep] == "*") {
                            linesDeep[deep - 1].append("<em>" + linesDeep[deep].toString() + "</em>");
                            linesDeep[deep] = new StringBuilder();
                            deep -= 1;
                        } else {
                            deep += 1;
                            marksDeep[deep] = "*";
                        }
                        index += 1;
                    } else if (index + 1 < s.length() && s.charAt(index + 1) == '*') {
                        if (marksDeep[deep] == "**") {
                            linesDeep[deep - 1].append("<strong>" + linesDeep[deep].toString() + "</strong>");
                            linesDeep[deep] = new StringBuilder();
                            deep -= 1;
                        } else {
                            deep += 1;
                            marksDeep[deep] = "**";
                        }
                        index += 2;
                    }

                } else if (notmark(index, s) && s.charAt(index) == '_') {
                    if (index + 1 < s.length() && (index + 1 < s.length() && s.charAt(index + 1) != '_')) {
                        if (marksDeep[deep] == "_") {
                            linesDeep[deep - 1].append("<em>" + linesDeep[deep].toString() + "</em>");
                            linesDeep[deep] = new StringBuilder();
                            deep -= 1;
                        } else {
                            deep += 1;
                            marksDeep[deep] = "_";
                        }
                        index += 1;
                    } else if (index + 1 < s.length() && s.charAt(index + 1) == '_') {
                        if (marksDeep[deep] == "__") {
                            linesDeep[deep - 1].append("<strong>" + linesDeep[deep].toString() + "</strong>");
                            linesDeep[deep] = new StringBuilder();
                            deep -= 1;
                        } else {
                            deep += 1;
                            marksDeep[deep] = "__";
                        }
                        index += 2;
                    }
                } else if (notmark(index, s) && index + 1 < s.length() && s.charAt(index) == '-'
                        && s.charAt(index + 1) == '-') {
                    if (marksDeep[deep] == "--") {
                        linesDeep[deep - 1].append("<s>" + linesDeep[deep].toString() + "</s>");
                        linesDeep[deep] = new StringBuilder();
                        deep -= 1;
                    } else {
                        deep += 1;
                        marksDeep[deep] = "--";
                    }
                    index += 2;
                } else if (notmark(index, s) && s.charAt(index) == '`') {
                    if (index + 2 < s.length() && s.charAt(index + 1) == '`' && s.charAt(index + 2) == '`') {
                        deep += 1;
                        marksDeep[deep] = "```";
                        index += 3;
                    } else {
                        if (marksDeep[deep] == "`") {
                            linesDeep[deep - 1].append("<code>" + linesDeep[deep].toString() + "</code>");
                            linesDeep[deep] = new StringBuilder();
                            deep -= 1;
                        } else {
                            deep += 1;
                            marksDeep[deep] = "`";
                        }
                        index += 1;
                    }
                } else {
                    if (codes.containsKey(s.charAt(index))) {
                        linesDeep[deep].append(String.valueOf(codes.get(s.charAt(index))));
                    } else {
                        linesDeep[deep].append(String.valueOf(s.charAt(index)));
                    }
                    index += 1;
                }
            }
        }
        while (deep > 0) {
            linesDeep[deep - 1].append(marksDeep[deep] + linesDeep[deep].toString());
            linesDeep[deep] = new StringBuilder();
            deep -= 1;
        }
        if (flag == 0) {
            linesDeep[0].append("</p>" + "\n");
        } else {
            linesDeep[0].append("</h" + Integer.toString(cnt) + ">" + "\n");
        }
        return linesDeep[0].toString();
    }
}