package com.okunu.dp;

/*
 * 最长公共子序列
 */
public class MaxLcs {

    public static int lcs(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] c = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            c[i][0] = 0;
        }
        for (int i = 0; i <= len2; i++) {
            c[0][i] = 0;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                }else {
                    c[i][j] = Math.max(c[i-1][j], c[i][j-1]);
                }
            }
        }
        return c[len1][len2];
    }
    
    public static void main(String[] args) {
        testLcs();
    }
    
    public static void testLcs(){
        String s1 = "abcbdab";
        String s2 = "bdcaba";
        System.out.println(lcs(s1, s2));
    }
}
