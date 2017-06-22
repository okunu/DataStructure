package com.okunu.tree;

public class SearchTree {

    public SearchNode mRoot;
    
    class SearchNode{
        public int value;
        public SearchNode left;
        public SearchNode right;
        public SearchNode parent;
        
        public SearchNode(){};
        public SearchNode(int v){
            value = v;
        }
        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(" v = " + value);
            if (left != null) {
                buffer.append("  left = " + left.value);
            }
            if (right != null) {
                buffer.append("  right = " + right.value);
            }
            if (parent != null) {
                buffer.append("  parent = " + parent.value);
            }
            return buffer.toString();
        }
    }
    
    //插入算法相对较简单，就是根据值找位置，如果小，找左子树，如果大了，往右子树，直到某个节点为空
    //但这种插入算法，有个问题，插入数值的顺序不同，得到的二叉搜索树就会不同。
    public void insert(SearchTree tree, int v){
        SearchNode node= new SearchNode(v);
        
        SearchNode x = tree.mRoot;
        SearchNode y = null;
        while (x != null) {
            y = x;
            if (v <= x.value) {
                x = x.left;
            }else {
                x = x.right;
            }
        }
        if (y == null) {
            mRoot = node;
            node.parent = null;
            return;
        }
        if (v <= y.value) {
            y.left = node;
            node.parent = y;
        }else {
            y.right = node;
            node.parent = y;
        }
    }
    
    public void middlePrint(SearchNode node){
        if (node != null) {
            middlePrint(node.left);
            System.out.print(node.value + "    ");
            middlePrint(node.right);
        }
    }
    
    public void prePrint(SearchNode node){
        if (node != null) {
            System.out.print(node.value + "    ");
            prePrint(node.left);
            prePrint(node.right);
        }
    }
    
    public SearchNode search(SearchNode node, int v){
        if (node == null || node.value == v) {
            return node;
        }
        if (v < node.value) {
            return search(node.left, v);
        }else {
            return search(node.right, v);
        }
    }
    
    public SearchNode search2(SearchNode node, int v){
        SearchNode temp = node;
        while (temp != null && temp.value != v) {
            if (v < temp.value) {
                temp = temp.left;
            }else {
                temp = temp.right;
            }
        }
        return temp;
    }
    
    public SearchNode treeMin(SearchNode node){
        SearchNode x = node;
        while (x != null && x.left != null) {
            x = x.left;
        }
        return x;
    }
    
    public SearchNode treeMax(SearchNode node){
        SearchNode x = node;
        while (x != null && x.right != null) {
            x = x.right;
        }
        return x;
    }
    
    public SearchNode followUp(SearchNode x){
        if (x.right != null) {
            return treeMin(x.right);
        }
        SearchNode y = x.parent;
        //当父节点是左子节点时，查找完成
        while (y != null && y.right == x) {
            x = y;
            y = y.parent;
        }
        return y;
    }
    
    public SearchNode preCucsor(SearchNode x){
        if (x.left != null) {
            return treeMax(x.left);
        }
        return x.parent;
    }
    
    public void swap(SearchNode u, SearchNode v){
        if (u.parent == null) {
            mRoot = v;
        }else if (u.parent.left == u) {
            u.parent.left = v;
        }else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }
    
    public void delete(SearchNode z){
        SearchNode y = null;
        if (z == null) {
            return;
        }
        if (z.left == null) {
            swap(z, z.right);
        }else if (z.right == null) {
            swap(z, z.left);
        }else {
            //寻找Z的后继节点，此处改为  treeMin(z.right)
            //为啥寻找z的后续节点就可以替换z的位置，二叉搜索树如果是中序遍历，那么一定是按从小到大排列的，
            //如果删除某个数之后，这个特性段然存在，从这个角度来看，就得找它的后续节点来替代z
            y = followUp(z);
            if (y.parent != z) {
                //本例中，将y的父节点和y的关系切断
                swap(y, y.right);
                //设置y的右节点为z的右节点
                y.right = z.right;
                //设置y右节点的父节点
                y.right.parent = y;
            }
            swap(z, y);
            //设置y左节点及其父节点
            y.left = z.left;
            y.left.parent = y;
        }
    }
    
    public static void main(String[] args) {
        SearchTree tree = new SearchTree();
        int[] array = {4, 5, 2, 1, 0, 9, 3, 7, 6, 8, 11, 10, 12};
        for (int i = 0; i < array.length; i++) {
            tree.insert(tree, array[i]);
        }
        tree.middlePrint(tree.mRoot);
        System.out.println();
//        tree.prePrint(tree.mRoot);
//        System.out.println();
        SearchNode temp = tree.search2(tree.mRoot, 9);
        System.out.println(temp);
        
        tree.delete(temp);
        tree.middlePrint(tree.mRoot);
        System.out.println();
        temp = tree.search2(tree.mRoot, 10);
        System.out.println(temp);
        
        
//        System.out.println(tree.treeMin(tree.mRoot));
//        System.out.println(tree.treeMax(tree.mRoot));
//        
//        System.out.println(tree.followUp(temp));
//        
//        System.out.println(tree.preCucsor(temp));
//        System.out.println(tree.preCucsor(tree.mRoot));
    }
}
