package com.okunu.btree;

import java.util.ArrayList;

import com.okunu.btree.BTree.BNode;

public class BTree {

    public static int t = 3;
    public BNode root;
    
    static class BNode{
        public int n;
        
        public int[] k = new int[2*t - 1];
        public BNode[] c = new BNode[2*t];
        
        public boolean leaf = true;
        
        public void print() {
            for (int i = 0; i < k.length; i++) {
                if (k[i] == Integer.MIN_VALUE) {
                    continue;
                }
                System.out.print((char)k[i] + "   ");
            }
            System.out.println("-----------");
//            System.out.println();
        }
    }
    
    /**
     * @param x 被分裂结点的父结点
     * @param i 被分列结点在父结点中的index
     * 分裂算法并不复杂，自己绘图，搞清楚上界下界，具体index等就可以了
     */
    public static void btreeSplitChild(BNode x, int i){
        BNode z = new BNode();
        BNode y = x.c[i];
        //z为分裂得到的新结点
        z.leaf = y.leaf;
        z.n = t - 1;
        //将被分裂结点的后一半关键字复制给z，同时删除后一半关键字
        for (int j = 0; j < t-1; j++) {
            z.k[j] = y.k[j+t];
            y.k[j+t] = Integer.MIN_VALUE;
        }
        //将被分裂结点的后一半子结点复制给z，同时置空后一半子结点
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.c[j] = y.c[j+t];
                y.c[j+t] = null;
            }
        }
        y.n = t-1;
        //后移一位x的关键字，给分裂上来的新关键字腾位置
        for (int j = x.n-1; j >= i; j--) {
            x.k[j+1] = x.k[j];
        }
        x.k[i] = y.k[t-1];
        y.k[t-1] = Integer.MIN_VALUE;
        //后移一位x的子结点，给分裂新增加的子结点腾位置
        for (int j = x.n; j >= i+1; j--) {
            x.c[j+1] = x.c[j];
        }
        x.c[i+1] = z;
        x.n = x.n+1;
    }
    
    public static BTree btreeCreate(){
        BNode x = new BNode();
        x.leaf = true;
        x.n = 0;
        BTree tree = new BTree();
        tree.root = x;
        return tree;
    }
    
    public static void btreeInsertNotFull(BNode x, int k){
        int i = x.n - 1;
        if (x.leaf) {
            //如果是叶结点，根据关键字大小排序，找出k的位置即可
            while (x.n > 0 && i >= 0 && k < x.k[i]) {
                x.k[i+1] = x.k[i];
                i--;
            }
            x.k[i+1] = k;
            x.n = x.n + 1;
        }else {
            //如果是内部结点，找出k对应的子树区域
            while (x.n > 0 && i >= 0 && k < x.k[i]) {
                i= i-1;
            }
            i = i+1;
            //如果路径中有某个结点是满结点，分裂它
            if (x.c[i].n == 2*t - 1) {
                btreeSplitChild(x, i);
                if (k > x.k[i]) {
                    i = i+1;
                }
            }
            //再次在子树中递归插入
            btreeInsertNotFull(x.c[i], k);
        }
    }
    
    public static void btreeInsert(BTree tree, int k){
        BNode r = tree.root;
        if (r.n == 2*t - 1) {
            BNode s = new BNode();
            s.leaf = false;
            tree.root = s;
            s.n = 0;
            s.c[0] = r;
            btreeSplitChild(s, 0);
            btreeInsertNotFull(s, k);
        }else {
            btreeInsertNotFull(r, k);
        }
    }
    
    public static void find(BNode x, int k){
        int i = 0;
        while (x.n > 0 && k > x.k[i]) {
            i++;
        }
        if (i < x.n && k == x.k[i]) {
            x.print();
            System.out.println(i);
            return;
        }
        if (x.leaf) {
            System.out.println("no result");
            return;
        }
        find(x.c[i], k);
    }
    
    public static void print(BNode x){
        if (x == null) {
            return;
        }
        x.print();
        for (int i = 0; i < x.c.length; i++) {
            print(x.c[i]);
        }
    }
    
    public static void main(String[] args) {
        BTree tree = btreeCreate();
        btreeInsert(tree, 'g');
        btreeInsert(tree, 'm');
        btreeInsert(tree, 'p');
        btreeInsert(tree, 'x');
        
        btreeInsert(tree, 'a');
        btreeInsert(tree, 'c');
        btreeInsert(tree, 'd');
        btreeInsert(tree, 'e');
        
        btreeInsert(tree, 'j');
        btreeInsert(tree, 'k');
        
        btreeInsert(tree, 'n');
        btreeInsert(tree, 'o');
        
        btreeInsert(tree, 'r');
        btreeInsert(tree, 's');
        btreeInsert(tree, 't');
        btreeInsert(tree, 'u');
        btreeInsert(tree, 'v');
        
        btreeInsert(tree, 'y');
        btreeInsert(tree, 'z');
        
        print(tree.root);
        
        System.out.println("*******************************");
        find(tree.root, 'k');
    }
}
