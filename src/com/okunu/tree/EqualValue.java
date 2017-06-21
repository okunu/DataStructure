package com.okunu.tree;

public class EqualValue {

    class IntNode{
        public int value;
        public int parent;
        
        public IntNode(){};
        public IntNode(int value, int parent){
            this.value = value;
            this.parent = parent;
        }
    }
    
    public int find(IntNode[] tree, int i){
        int j = 0;
        //等价问题，默认节点里的value和在数组中的位置是相等的
        for (j = i; tree[j].parent > 0; j = tree[j].parent) {
        }
        return j;
    }
    
    public void merge(IntNode[] tree, int i, int j){
        tree[j].parent = i;
    }
    
    public IntNode[] init(int n, int k, int[] array){
        IntNode[] tree = new IntNode[n];
        for (int i = 0; i < array.length; i++) {
            tree[i].value = i;
            if (i == 0) {
                
            }
        }
        return tree;
    }
    
    public static void main(String[] args) {
        int n = 100;
        int k = 3;
        //集合中的所有值
        int[] array = {2, 3, 56, 23, 5, 9};
        //判断下列值是否在集合中
        int[] params = {3, 9};
        
    }
}
