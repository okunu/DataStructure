package com.okunu.dp;

/*
 * 钢条切割方案最大值求解
 */
public class Steel {

    public static int[] steel(int[] p){
        int[] s = new int[p.length];
        s[0] = 0;
        int max = 0;
        //因为长度为0的钢条，切割最大收益为0，S[0]已知，所以i从1开始
        for (int i = 1; i < p.length; i++) {
            max = 0;
            for (int j = 1; j <= i; j++) {
                //j也必须从1开始，如果从0开始，i等于1时，s[i-j]就等于s[1]了，而s[1]还是未知值
                if (max < (p[j] + s[i-j])) {
                    max = p[j] + s[i-j];
                }
            }
            s[i] = max;
        }
        return s;
    }
    
    public static void main(String[] args) {
        test();
    }
    
    public static void test(){
        int[] p = new int[]{0,1,5,8,9,10,17,17,20,24,30};
        int[] r = steel(p);
        for (int i = 0; i < r.length; i++) {
            System.out.print(r[i] + "   ");
        }
        System.out.println();
    }
}
