package com.okunu.basic;

public class Node {

    private String mValue;
    private Node mLeftChild, mRightChild;

    public Node() {

    }

    public Node(String str) {
        mValue = str;
    }

    public void assign(Node left, Node right) {
        mLeftChild = left;
        mRightChild = right;
    }

    public boolean hasLeftChild() {
        return mLeftChild != null;
    }

    public boolean hasRightChild() {
        return mRightChild != null;
    }

    public Node getLeftChild() {
        return mLeftChild;
    }

    public Node getRightChild() {
        return mRightChild;
    }

    public void setLeftChild(Node node){
        mLeftChild = node;
    }
    
    public void setRightChild(Node node){
        mRightChild = node;
    }
    
    @Override
    public String toString() {
        return mValue;
    }
    
    public String getValue(){
        return mValue;
    }

}
