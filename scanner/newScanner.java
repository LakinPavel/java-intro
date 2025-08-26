package scanner;

import java.io.*;

public class newScanner {
    private int end = 1;
    private Reader inputReader;
    private int leghtBuffer;
    private int indexBuffer;
    public static StringBuilder perLines = new StringBuilder();

    public static int flag = 0;

    public void close() throws IOException {
        this.inputReader.close();
    }

    private char[] buffer = new char[128];


    private boolean checkWhiteSpace(char el) {
        return Character.SPACE_SEPARATOR == Character.getType(el);
    }

    private boolean checkNextLine(String s) {
        return System.lineSeparator().contains(s);

    }

    private boolean checkNextLine(char el) {
        if (System.lineSeparator().contains(Character.toString(el))){
            // flag += 1;
            perLines.append(el);
            // if (checkNextLine(perLines.toString())){
            //     flag += 1;
            //     perLines.setLength(0);
            // }
            return true;
        }
        else{
            return false;
        }
    }


    public newScanner(InputStream inputScanner) throws IOException, FileNotFoundException {
        this.inputReader = new InputStreamReader(inputScanner, "UTF-8");
    }

    public newScanner(String inputReader, String encoding) throws IOException, FileNotFoundException {
        this.inputReader = new InputStreamReader(new FileInputStream(inputReader), encoding);
    }

    public newScanner(String str) throws IOException, FileNotFoundException {
        this.inputReader = new StringReader(str);
    }

    private void fillBuffer() {
        try {
            this.leghtBuffer = inputReader.read(buffer);
        } catch (IOException e) {
            this.end = -1;
        }
        this.indexBuffer = 0;
        if (leghtBuffer == -1){
            end = -1;
        }
    }

    public boolean hasNext() throws IOException {
        if (end == -1) {
            return false;
        }
        while (checkNextLine(buffer[indexBuffer]) || checkWhiteSpace(buffer[indexBuffer])) {
            if (indexBuffer + 1 < leghtBuffer) {
                indexBuffer += 1;
            } else {
                fillBuffer();
            }
        }
        if (end != -1) {
            return true;
        }
        return false;
    }

    public String next() throws IOException {
        if (hasNext()) {
            StringBuilder word = new StringBuilder();
            while (end != -1 && !checkNextLine(buffer[indexBuffer]) && !checkWhiteSpace(buffer[indexBuffer])) {
                // word.append(perLines.toString());
                perLines.setLength(0);
                word.append(buffer[indexBuffer]);
                if (indexBuffer + 1 < leghtBuffer) {
                    indexBuffer += 1;
                    // flag = 0;
                } else {
                    fillBuffer();
                }
            }
            return word.toString();
        }
        throw new IOException();
    }

    // public boolean hasNextLine() throws IOException {
    //     if (end == -1) {
    //         return false;
    //     }
    //     while (checkNextLine(buffer[indexBuffer])) {
    //         if (indexBuffer + 1 < leghtBuffer) {
    //             indexBuffer += 1;
    //         } else {
    //             fillBuffer();
    //         }
    //     }
    //     if (end != -1) {
    //         // indexBuffer -= 1; //так как мы ведь смотрели следующий, но вопрос, а если это было на переходе (то было конец, а это уже 0 и я делаю -)
    //         return true;
    //     }
    //     return false;
    // }

    // public String nextLine() throws IOException{
    //     if (hasNextLine()) {
    //         StringBuilder line = new StringBuilder();
    //         while (!checkNextLine(buffer[indexBuffer]) && end != -1) {
    //             line.append(buffer[indexBuffer]); 
    //             if (indexBuffer + 1 < leghtBuffer) {
    //                 indexBuffer += 1;
    //             } else {
    //                 fillBuffer();
    //             }
    //         }
    //         // indexBuffer -= 1; //так как мы ведь смотрели следующий, но вопрос, а если это было на переходе (то было конец, а это уже 0 и я делаю -)
    //         return line.toString();
    //     }
    //     throw new IOException();
    // }
}