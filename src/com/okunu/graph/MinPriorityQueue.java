package com.okunu.graph;

public class MinPriorityQueue<T extends Comparable<? super T>> {

    public int mHeapSize;
    private T[] mData;
    
    private void minHeapify(int i){
        int l = 2*i + 1;
        int r = 2*i + 2;
        int small = i;
        if (l < mHeapSize && mData[i].compareTo(mData[l]) > 0) {
            small = l;
        }else {
            small = i;
        }
        if (r < mHeapSize && mData[small].compareTo(mData[r]) > 0) {
            small = r;
        }
        if (small != i) {
            T temp = mData[i];
            mData[i] = mData[small];
            mData[small] = temp;
            minHeapify(small);
        }
    }
    
    public void buildMinHeap(){
        mHeapSize = mData.length;
        for (int i = mHeapSize/2; i >= 0; i--) {
            minHeapify(i);
        }
    }

    public void checkMinHeap(){
        for (int i = mHeapSize/2; i >= 0; i--) {
            minHeapify(i);
        }
    }
    
    public T heapMin(){
        if (mHeapSize >= 0) {
            return mData[0];
        }
        return null;
    }
    
    public int size(){
        return mHeapSize;
    }
    
    public T heapExtractMin(){
        if (mHeapSize == 0) {
            System.out.println("heap is empty");
            return null;
        }
        int i = mHeapSize - 1;
        T min = mData[0];
        mData[0] = mData[i];
        mHeapSize--;
        minHeapify(0);
        mData[i] = null;
        return min;
    }
    
    public int getIndex(T obj){
        for (int i = 0; i < mHeapSize; i++) {
            if (obj.equals(mData[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public void heapChangeKey(int i, T key){
        if (i < 0) {
            return;
        }
        if (key.compareTo(mData[i]) > 0) {
            mData[i] = key;
            minHeapify(i);
        }else if (key.compareTo(mData[i]) < 0 ) {
            int parent = getParent(i);
            mData[i] = key;
            //如果i小于parent，因为最小堆中子节点要大于父节点
            while (parent != Integer.MIN_VALUE && mData[i].compareTo(mData[parent]) < 0) {
                T temp = mData[i];
                mData[i] = mData[parent];
                mData[parent] = temp;
                i = parent;
                parent = getParent(parent);
            }
        }
    }
    
    public static int getParent(int i){
        if (i == 0) {
            return Integer.MIN_VALUE;
        }else {
            if (i%2 == 0) {
                return (i-1)/2;
            }else {
                return i/2;
            }
        }
    }
    
    public void print(){
        for (int i = 0; i < mHeapSize; i++) {
            System.out.print(mData[i] + "   ");
        }
        System.out.println("    heapsize = " + mHeapSize);
    }
    
    public static void main(String[] args) {
        Integer[] array = {4,1,3,2,16,9,10,14,8,7,5};
        MinPriorityQueue queue = new MinPriorityQueue(array);
        queue.print();
        Integer min = (Integer) queue.heapExtractMin();
        System.out.println(min);
        queue.print();
        min = (Integer) queue.heapExtractMin();
        System.out.println(min);
        queue.print();
        
        System.out.println("-----------");
        queue.heapChangeKey(1, 6);
        queue.print();
    }
    
    public MinPriorityQueue(T[] array){
        mData = array;
        buildMinHeap();
    }
}
