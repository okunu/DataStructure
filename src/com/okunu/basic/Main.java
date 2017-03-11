package com.okunu.basic;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        Node root = tree.generateTree();

        System.out.println("----------前序---------");
        tree.preTraverse(root);

        System.out.println("\n" + "------------");
        tree.preTraverseByStack(root);

        System.out.println("----------中序---------");
        tree.middleTraverse(root);

        System.out.println("\n" + "------------");
        tree.middleTraverseByStack(root);

        System.out.println("----------后序---------");
        tree.lastTraverse(root);

        System.out.println("\n" + "------------");
        tree.lastTraverseByStack(root);

        System.out.println("\n" + "----------顺序---------");
        tree.traverse(root);
    }
}
