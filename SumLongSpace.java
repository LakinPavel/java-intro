public class SumLongSpace {
    public static void main(String[] args) {
        long answer = 0;
        int begin = 0;
        for (int i = 0; i < args.length; i++) {
            int len = 0;
            for (int j = 0; j < args[i].length(); j++) {
                int flag = 0;
                if (Character.SPACE_SEPARATOR != Character.getType(args[i].charAt(j))) {
                    flag = 1;
                    len++;
                    if (len == 1){
                        begin = j;
                    }
                }
                if (((j == args[i].length() - 1) || (flag == 0)) && (len > 0))  {
                        answer += Long.parseLong(args[i].substring(begin, begin + len));
                        len = 0;
                }
            }
        }
        System.out.println(answer);
    }
}