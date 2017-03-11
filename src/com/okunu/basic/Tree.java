package com.okunu.basic;

public class Tree {

    public Node generateTree() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");
        Node i = new Node("i");

        a.assign(b, c);
        b.assign(d, e);
        c.assign(f, g);
        e.assign(h, i);
        return a;
    }

    public void preTraverse(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root + "   ");
        preTraverse(root.getLeftChild());
        preTraverse(root.getRightChild());
    }

    public void preTraverseByStack(Node root) {
        if (root == null) {
            return;
        }
        DemoStack<Node> stack = new DemoStack<>();
        stack.push(root);
        Node temp = null;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            System.out.print(temp + "   ");
            if (temp.hasRightChild()) {
                stack.push(temp.getRightChild());
            }
            if (temp.hasLeftChild()) {
                stack.push(temp.getLeftChild());
            }
        }
        System.out.println();
    }

    public void middleTraverse(Node root) {
        if (root == null) {
            return;
        }
        middleTraverse(root.getLeftChild());
        System.out.print(root + "   ");
        middleTraverse(root.getRightChild());
    }

    public void middleTraverseByStack(Node root){
        if (root == null) {
            return;
        }
        DemoStack<Node> stack = new DemoStack<>();
        stack.push(root);
        Node temp = null;
        while (!stack.isEmpty()) {
            while (stack.peek() != null && stack.peek().hasLeftChild()) {
                stack.push(stack.peek().getLeftChild());
            }
            if (stack.peek() == null) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                temp = stack.pop();
                System.out.print(temp + "   ");
                stack.push(temp.getRightChild());
            }
        }
        System.out.println();
    }
    
    public void lastTraverse(Node root){
        if (root == null) {
            return;
        }
        lastTraverse(root.getLeftChild());
        lastTraverse(root.getRightChild());
        System.out.print(root + "   ");
    }
    
    public void lastTraverseByStack(Node root){
        if (root == null) {
            return;
        }
        DemoStack<Node> stack = new DemoStack<>();
        stack.push(root);
        Node cur = null;
        Node pre = null;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ((cur.getLeftChild() == null && cur.getRightChild() == null)
                    || (pre != null && (pre == cur.getLeftChild() || pre == cur.getRightChild()))) {
                System.out.print(cur + "   ");
                stack.pop();
                pre = cur;
            }else {
                if (cur.hasRightChild()) {
                    stack.push(cur.getRightChild());
                }
                if (cur.hasLeftChild()) {
                    stack.push(cur.getLeftChild());
                }
            }
            
        }
    }
    
    public void traverse(Node root){
        if (root == null) {
            return;
        }
        DemoQueue<Node> queue = new DemoQueue<>();
        queue.enqueue(root);
        Node temp = null;
        while (queue.getSize() > 0) {
            temp = queue.dequeue();
            System.out.print(temp + "   ");
            if (temp.hasLeftChild()) {
                queue.enqueue(temp.getLeftChild());
            }
            if (temp.hasRightChild()) {
                queue.enqueue(temp.getRightChild());
            }
        }
        System.out.println();
    }
}
