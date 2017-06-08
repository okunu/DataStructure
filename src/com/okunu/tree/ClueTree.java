package com.okunu.tree;

import com.okunu.basic.Node;
import com.okunu.tree.ClueNode.TAG;

public class ClueTree {

    public ClueNode generateTree() {
        ClueNode a = new ClueNode("a");
        ClueNode b = new ClueNode("b");
        ClueNode c = new ClueNode("c");
        ClueNode d = new ClueNode("d");
        ClueNode e = new ClueNode("e");
        ClueNode f = new ClueNode("f");
        ClueNode g = new ClueNode("g");
        ClueNode h = new ClueNode("h");
        ClueNode i = new ClueNode("i");
        ClueNode j = new ClueNode("j");

        a.assign(b, c);
        b.assign(d, e);
        c.assign(f, g);
        d.assign(h, i);
        e.assign(j, null);
        return a;
    }
    
    private void printTree(ClueNode node){
        if (node == null) {
            return;
        }
        printTree((ClueNode)node.getLeftChild());
        
        System.out.println("value = " + node.getValue() + "  lefttag = " + node.getLeftTag()
                + "  leftchild = " + node.getLeftChild() + "  righttag = " + node.getRightTag()
                + "  rightchild = " + node.getRightChild());
        
        printTree((ClueNode)node.getRightChild());
    }
    
    private void printNode(ClueNode node){
        System.out.println("value = " + node.getValue() + "  lefttag = " + node.getLeftTag()
                + "  leftchild = " + node.getLeftChild() + "  righttag = " + node.getRightTag()
                + "  rightchild = " + node.getRightChild());
    }
    
    private void printClueTree(ClueNode node){
        ClueNode p = node;
        while (p != null) {
            while(p.getLeftTag() == TAG.LINK && p.hasLeftChild()) {
                p = (ClueNode) p.getLeftChild();
            }
            printNode(p);
            while(p.getRightTag() == TAG.THREAD && p.hasRightChild()) {
                p = (ClueNode) p.getRightChild();
                printNode(p);
            }
            p = (ClueNode) p.getRightChild();
        }
    }
    
    public static void main(String[] args) {
        ClueTree tree = new ClueTree();
        ClueNode node = tree.generateTree();
        
//        tree.printTree(node);
//        System.out.println(node);
        
        tree.makeClueTree(node);
        
        tree.printClueTree(node);
    }
    
    
    ClueNode pre;
    private void makeClueTree(ClueNode node){
        if (node == null) {
            return;
        }
        makeClueTree((ClueNode)node.getLeftChild());
        
        if (!node.hasLeftChild()) {
            node.setLeftTag(TAG.THREAD);
            node.setLeftChild(pre);
        }
        if (pre != null && !pre.hasRightChild()) {
            pre.setRightTag(TAG.THREAD);
            pre.setRightChild(node);
        }
        
        pre = node;
        
        makeClueTree((ClueNode)node.getRightChild());
    }
    
    
}
