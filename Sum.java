import static java.lang.Integer.parseInt;

public class Sum {
    public static void main(String[] args) {
        int an = 0;
        int l1 = -1;
        for (int i = 0; i < args.length; i++) {
            int le = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (!Character.isWhitespace(args[i].charAt(j))) {
                    if (le == 0) {
                        l1 = j;
                        le = 1;
                    } else {
                        le++;
                    }
                } else {
                    if (le > 0) {
                        an += parseInt(args[i].substring(l1, l1 + le));
                        le = 0;
                    }
                }
                if (j == args[i].length() - 1 && le != 0) {
                    an += parseInt(args[i].substring(l1));
                    le = 0;
                }
            }
        }
        System.out.println(an);
    }
}
