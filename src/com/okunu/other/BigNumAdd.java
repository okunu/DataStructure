package com.okunu.other;

import java.util.Arrays;

public class BigNumAdd {

	public static void main(String[] args) {
//		int[] num1 = createIntArray("8948082578242");
//		reverseArray(num1);
		bigNumAdd("145","23");
	}
	
	public static void reverseArray(int[] array){
		for (int i = 0, end = array.length - 1 - i; i < end; i++, end--) {
			int temp = array[i];
			array[i] = array[end];
			array[end] = temp;
		}
//		System.out.println(Arrays.toString(array));
	}
	
	public static int[] createIntArray(String num){
		int[] array = new int[num.length()];
		for (int i = 0; i < num.length(); i++) {
			array[i] = Integer.parseInt(num.charAt(i)+"");
		}
		//System.out.println("orgin num = " + num);
		System.out.println(" createIntArray " + Arrays.toString(array));
		return array;
	}
	
	public static int[] bigNumAdd(String s1, String s2){
		//根据字符串创建int型 数组
		int[] n1 = createIntArray(s1);
		int[] n2 = createIntArray(s2);
		//结果数组的长度，应该是最长数组长度加1，因为两个数相加，最多结果增加1位数，不可能增加两位及以上
		int rLength = n1.length > n2.length ? n1.length + 1 : n2.length + 1;
		int[] result = new int[rLength];
		
		//将两个数组倒序，以便个位数在数组前头，方便计算，实现对齐
		reverseArray(n1);
		reverseArray(n2);
		int temp = 0;
		int bTemp = 0;
		int num1 = 0;
		int num2 = 0;
		//遍历次数为result.length - 1，就是最长相加数组长度
		for (int i = 0; i < result.length - 1; i++) {
			//保证两个相加数不会数组越界
			if (i < n1.length) {
				num1 = n1[i];
			}else {
				num1 = 0;
			}
			
			if (i < n2.length) {
				num2 = n2[i];
			}else {
				num2 = 0;
			}
			//两个同位置的数字相加，注意还要加原本位置上的结果元素，因为前一步计算中可能产生进位
			temp = num1 + num2 + result[i];
			//计算进位，如果相加结果大于10，肯定有进位，因为是两个数相加，进位值只能是1
			if (temp >= 10) {
				bTemp = 1;
				temp = temp - 10;
			}else {
				bTemp = 0;
			}
			//设置当前位的结果数值以及下一位的进位值
			result[i] = temp;
			if (bTemp > 0) {
				result[i+1] = bTemp;
			}
		}
		//除去结果中高位多余的0
		result = removeZero(result);
		//再将倒序结果，得到期望的结果值
		reverseArray(result);
		System.out.println(" bigNumAdd " + Arrays.toString(result));
		return result;
	}
	
	public static int[] removeZero(int[] array){
		int count = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] == 0) {
				count ++;
			}
		}
		if (count > 0) {
			int[] result = new int[array.length - count];
			for (int i = 0; i < result.length; i++) {
				result[i] = array[i];
			}
			array = result;
		}
		return array;
	}
}
