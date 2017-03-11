package com.okunu.basic;

public class DemoQueue<T> {

    private int mTop;
    private int mTail;
    private static final int MAX = 10;
    private Object[] mArray;
    private int mLength = 0;
    
    public DemoQueue(){
        mTop = mTail = 0;
        mArray = new Object[MAX];
        mLength = 0;
    }
    
    public int getSize(){
//        if (mTop >= mTail) {
//            return mTop - mTail;
//        }else {
//            return MAX - mTail + mTop;
//        }
        return mLength;
    }
    
    public boolean enqueue(T e){
        if (getSize() == MAX) {
            System.out.println("queue is full");
            return false;
        }
        if (mTop == MAX) {
            mTop -= MAX;
        }
        mArray[mTop] = e;
        mTop ++;
        mLength++;
        return true;
    }
    
    public T dequeue(){
        if (getSize() == 0) {
            System.out.println("queue is empty");
            return null;
        }
        int index = mTail;
        mTail++;
        if (mTail == MAX) {
            mTail -= MAX;
        }
        mLength--;
        return (T) mArray[index];
    }
    
    private void print(){
        System.out.println("  mTail = "+ mTail + "  mTop = " + mTop + "  size = " + getSize());
        int j = 0;
//        for (int i = mTail; i < mTail+getSize(); i++) {
//            j = (i == MAX) ? i-MAX : i;
//            System.out.print(mArray[j] + "   ");
//        }
        for (int i = 0; i < getSize(); i++) {
            j = i + mTail;
            j = (j >= MAX) ? j-MAX : j;
            System.out.print(mArray[j] + "   ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        DemoQueue<Integer> queue = new DemoQueue<>();
//        queue.enqueue(1);
//        queue.enqueue(2);
//        queue.enqueue(3);
//        queue.print();
//        queue.enqueue(4);
//        queue.print();
//        
//        System.out.println("----------");
//        queue.dequeue();
//        queue.dequeue();
//        queue.print();
//        
//        System.out.println("-----------");
//        queue.enqueue(20);
//        queue.print();
        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(2);
        queue.enqueue(3);
//        queue.print();
        
        queue.dequeue();
        queue.enqueue(4);
        queue.enqueue(5);
        
        queue.print();
    }
}
