package com.okunu.sort;

public class CountSort {

	/**
	 * @param arrayA 待排序数组
	 * @param arrayB 返回的正确排序的数组
	 * @param k 数组A中的元素最大值
	 */
	public static int[] countSort(int[] arrayA, int[] arrayB, int k){
		int value = 0;
		int pos = 0;
		
		int size = arrayA.length;
		int[] arrayC = new int[k + 1];
		for (int i = 0; i <= k; i++) {
			arrayC[i] = 0;
		}
		//数组a中每一个value出现的个数，存储在c中，因为c中长度为k+1
		//而a中元素最大值为k，所以可以存下这些值
		for (int i = 0; i < size; i++) {
			arrayC[arrayA[i]]++;
		}
		//数组C[i]中存放的是a中i元素出现的个数，所以C[i]+C[i-1],则是不大于i的元素的个数
		for (int i = 1; i <= k; i++) {
			arrayC[i] = arrayC[i] + arrayC[i-1];
		}
		//从数组c中取出a数组中元素不大于自己的个数，然后放入新数组b中，则排序已完成
		for (int i = size-1; i >= 0; i--) {
			value = arrayA[i];
			pos = arrayC[value];
			arrayB[pos-1]=value;
			arrayC[value]--;
		}
		return arrayB;
	}
	
	public static int getBitData(int value, int index){
		while (index > 0) {
			value = value/10;
			index--;
		}
		return value%10;
	}

	public static int[] countSort2(int[] arrayA, int index){
		int value = 0;
		int pos = 0;
		//个、十、百等位置上最大值为9
		int k = 9;
		
		int size = arrayA.length;
		int[] arrayB = new int[size];
		int[] arrayC = new int[k + 1];
		
		for (int i = 0; i <= k; i++) {
			arrayC[i] = 0;
		}
		//数组c中存的是数组a某个具体数位上的值的出现次数
		for (int i = 0; i < size; i++) {
			arrayC[getBitData(arrayA[i], index)]++;
		}
		for (int i = 1; i <= k; i++) {
			arrayC[i] = arrayC[i] + arrayC[i-1];
		}
		//注意b中存放的是数组a中的值。
		for (int i = size-1; i >= 0; i--) {
			value = getBitData(arrayA[i], index);
			pos = arrayC[value];
			arrayB[pos-1]=arrayA[i];
			arrayC[value]--;
		}
		return arrayB;
	}
	
	public static int[] radixSort(int[] arrayA,int index){
		for (int i = 0; i < index; i++) {
			arrayA = countSort2(arrayA, i);
		}
		return arrayA;
	}
	
	public static void main(String[] args) {
//		int[] arrayA = {2,5,3,0,2,3,0,4};
//		int[] arrayB = new int[arrayA.length];
//		arrayB = countSort(arrayA, arrayB, 5);
//		print(arrayB);
//		
//		System.out.println(getBitData(529, 2));
		
		int[] arrayA = {326,453,608,835,751,435,704,690};
		arrayA = radixSort(arrayA, 3);
		print(arrayA);
	}
	
    public static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println();
    }
}
