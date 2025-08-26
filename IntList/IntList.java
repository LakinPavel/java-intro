package IntList;
import java.util.Arrays;


public class IntList {
    private int[] list = new int[10];
    int len = 0;

    public static int[] alloc(int[] listA) {
        int[] newList = Arrays.copyOf(listA, 2 * listA.length);
        return newList;
    }

    public void append(int x){
        if (len + 4 < list.length){
            alloc(this.list);
        }
        this.list[len] = x;
        len += 1;
    }

    public int get(int index){
        return list[index];
    }

    // public int[] view(){
    //     return list;
    // }
}

