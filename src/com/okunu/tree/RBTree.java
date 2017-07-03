package com.okunu.tree;

//  http://www.cnblogs.com/skywang12345/p/3245399.html
public class RBTree {

	public static final int RED = 1;
	public static final int BLACK = 2;
	public RBNode mRoot = new RBNode();
	
	public RBTree(int value){
		mRoot = new RBNode(value);
		mRoot.color = BLACK;
		mRoot.parent = null;
		mRoot.left = null;
		mRoot.right = null;
	}
	
	public RBTree(){
		mRoot = null;
	}
	
	public void leftRotate(RBNode x){
		RBNode y = x.right;
		if (y == null) {
			return;
		}
		//处理x的right，因为x的right本来为y，确定不再为x的right了
		//把y的left作为x新的right
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		//处理y的父节点
		y.parent = x.parent;
		if (x.parent == null) {
			mRoot = y;
		}else if (x == x.parent.left) {
			x.parent.left = y;
		}else {
			x.parent.right = y;
		}
		//处理y的左子节点，将y的左子节点设置为x
		y.left = x;
		x.parent = y;
	}
	
	public void rightRotate(RBNode x){
		RBNode y = x.left;
		if (y == null) {
			return;
		}
		x.left = y.right;
		if (y.right != null) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			mRoot = y;
		}else if (x == x.parent.left) {
			x.parent.left = y;
		}else {
			x.parent.right = y;
		}
		y.right = x;
		x.parent = y;
	}
	
	public void rb_insert(RBNode z){
		RBNode y = null;
		RBNode x = mRoot;
		while (x != null) {
			y = x;
			if (z.value < x.value) {
				x = x.left;
			}else {
				x = x.right;
			}
		}
		z.parent = y;
		if (y == null) {
			mRoot = z;
		}else if (z.value < y.value) {
			y.left = z;
		}else {
			y.right = z;
		}
		z.left = null;
		z.right = null;
		z.color = RED;
		rb_insert_fixup(z);
	}
	
	//添加修复核心思想为，将多作的红色节点向根节点移动，最后把根节点弄成黑色即可
	public void rb_insert_fixup(RBNode z){
		RBNode y = null;
		while (z.parent != null && z.parent.color == RED) {
			if (z.parent == z.parent.parent.left) {
				y = z.parent.parent.right;
				//z的父节点为红，叔叔节点也为红
				if (y.color == RED) {
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				}else if (z == z.parent.right) {
					//叔叔节点为黑，且z是z的父亲的右子节点
					z = z.parent;
					leftRotate(z);
				}else {
					//叔叔节点为黑，且z是z的父亲的左子节点
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					rightRotate(z.parent.parent);
				}
			}else {
				y = z.parent.parent.left;
				if (y.color == RED) {
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				}else if (z == z.parent.left) {
					//叔叔节点为黑，且z是z的父亲的右子节点
					z = z.parent;
					rightRotate(z);
				}else {
					//叔叔节点为黑，且z是z的父亲的左子节点
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					leftRotate(z.parent.parent);
				}
			}
		}
		mRoot.color = BLACK;
	}
	
	public void rb_transplant(RBNode u, RBNode v){
		if (u.parent == null) {
			mRoot = v;
		}else if (u == u.parent.left) {
		    u.parent.left = v ;
		}else {
		    u.parent.right = v;
		}
		if (v != null) {
		    v.parent = u.parent;
        }
	}
	
	public RBNode treeMin(RBNode node){
		RBNode x = node;
        while (x != null && x.left != null) {
            x = x.left;
        }
        return x;
    }
	
	public void rb_delete(RBNode z){
		RBNode y = z;
		RBNode x = null;
		int yOriginColor = y.color;
		if (z.left == null) {
			x = z.right;
			rb_transplant(z, z.right);
		}else if (z.right == null) {
			x = z.left;
			rb_transplant(z, z.left);
		}else {
			y = treeMin(z.right);
			yOriginColor = y.color;
			x = y.right;
			if (y.parent == z) {
				x.parent = y;
			}else {
				rb_transplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			rb_transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (yOriginColor == BLACK) {
		    rb_delete_fixup(x);
		}
	}
	
	//删除修复的核心思想是，x代替了y原来的位置，如果y是黑的，y位置移动了，那么规则5可能被破坏
	//如果想象x有两个颜色，自身加一个黑色，替补y的黑色，那规则5则不再破坏
	//如果x自身为红色加额外黑色，则此时所有规则都ok
	//如果x自身为黑，额外也是黑，那么规则5则被破坏
	//现在则要想办法将x弄成红色+黑色的模式，将多余黑色向根节点移动
	public void rb_delete_fixup(RBNode x){
	    if (x == null) {
            return;
        }
		RBNode w = null;
		while (x != mRoot && x.color == BLACK) {
			if (x == x.parent.left) {
				w = x.parent.right;
				if (w.color == RED) {
					w.color = BLACK;
					x.parent.color = RED;
					leftRotate(x.parent);
					w = x.parent.right;
				}else if ((w.left == null || w.left.color == BLACK)
				        && (w.right == null || w.right.color == BLACK)) {
					w.color = RED;
					x = x.parent;
				}else if (w.right == null || w.right.color == BLACK) {
					w.left.color = BLACK;
					w.color = RED;
					rightRotate(w);
					w = x.parent.right;
				}else {
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.right.color = BLACK;
					leftRotate(x.parent);
					x = mRoot;
				}
			}else {
				w = x.parent.left;
				if (w.color == RED) {
					w.color = BLACK;
					x.parent.color = RED;
					rightRotate(x.parent);
					w = x.parent.left;
				}else if ((w.left == null || w.left.color == BLACK) 
				        && (w.right == null || w.right.color == BLACK)) {
					w.color = RED;
					x = x.parent;
				}else if (w.left == null || w.left.color == BLACK) {
					w.right.color = BLACK;
					w.color = RED;
					leftRotate(w);
					w = x.parent.left;
				}else {
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.left.color = BLACK;
					rightRotate(x.parent);
					x = mRoot;
				}
			}
		}
		x.color = BLACK;
	}
	
	
	public static void main(String[] args) {
//		test_left_right_rotate();
		test_insert();
//	    test_delete();
	}
	
	public static void test_delete(){
	    RBTree tree = new RBTree();
        int[] array = new int[]{11,2,14,15,1,7,5,8,4};
        for (int i = 0; i < array.length; i++) {
            tree.rb_insert(new RBNode(array[i]));
        }
//        tree.middlePrint(tree.mRoot);
        System.out.println("  mroot = " + tree.mRoot);
        RBNode temp = tree.search(11);
        tree.rb_delete(temp);
        System.out.println(" after delete mroot = " + tree.mRoot);
        System.out.println("------------------------------");
        tree.middlePrint(tree.mRoot);
	}
	
	public static void test_insert(){
		RBTree tree = new RBTree();
		int[] array = new int[]{11,2,14,15,1,7,5,8,4};
		for (int i = 0; i < array.length; i++) {
			tree.rb_insert(new RBNode(array[i]));
		}
		tree.middlePrint(tree.mRoot);
	}
	
	public static void test_left_right_rotate(){
		RBTree tree = new RBTree();
		int[] array = new int[]{40, 20, 10, 30,  60, 50, 70};
		for (int i = 0; i < array.length; i++) {
			tree.rb_insert(new RBNode(array[i]));
		}
		tree.middlePrint(tree.mRoot);
		
		System.out.println("-----------");
		
		RBNode temp = tree.search(40);
		tree.rightRotate(temp);
//		tree.leftRotate(temp);
		tree.middlePrint(tree.mRoot);
	}
	
	public RBNode search(int v){
		RBNode temp = mRoot;
        while (temp != null && temp.value != v) {
            if (v < temp.value) {
                temp = temp.left;
            }else {
                temp = temp.right;
            }
        }
        return temp;
    }
	
	public void middlePrint(RBNode node){
        if (node != null) {
            middlePrint(node.left);
            System.out.println(node);
            middlePrint(node.right);
        }
    }
	
	static class RBNode{
        public int value;
        public RBNode left;
        public RBNode right;
        public RBNode parent;
        public int color = RED;
        
        public RBNode(Object obj){
        	if (obj == null) {
        		color = BLACK;
        		left = null;
        		right = null;
        		value = Integer.MIN_VALUE;
			}
        }
        
        public RBNode(){};
        public RBNode(int v){
            value = v;
            color = RED;
            left = null;
            right = null;
        }
        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(" v = " + value);
            String c = color == RED ? "red" : "black";
            buffer.append("  color = " + c);
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
}
