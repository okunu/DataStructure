package com.okunu.basic;

public class DemoStack<T> {

    private int mTop = 0;
    private static final int MAX = 20;
    private Object[] mArray = null;
    
    public DemoStack(){
        mTop = 0;
        mArray = new Object[MAX];
    }
    
    public int getSize(){
        return mTop;
    }
    
    public boolean push(T e){
        if (mTop < MAX) {
            mArray[mTop] = e;
            mTop ++;
            return true;
        }
        System.out.println("stack is full");
        return false;
    }
    
    public boolean isEmpty(){
        return mTop == 0;
    }
    
    public T pop(){
        if (mTop == 0) {
            System.out.println("stack is empty");
            return null;
        }
        T e = (T) mArray[--mTop];
        return e;
    }
    
    public T peek(){
        int index = mTop - 1;
        return (T) mArray[index];
    }
    
    public static void main(String[] args) {
        DemoStack<Integer> stack = new DemoStack<>();
        stack.push(10);
        System.out.println(stack.getSize() + "  peek = " + stack.peek());
        
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        
    }
}
