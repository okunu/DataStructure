package com.okunu.graph;

public class Vertex implements Comparable<Vertex>{
    public static enum COLOR{WHITE, BLACK, GRAY};
    
    public Object info;
    public int d;
    public int f;
    public COLOR color;
    public Arc firstArc;
    //为bellman ford添加的前驱参数
    public Vertex pre;
    
    
    public Vertex(Object info){
        this.info = info;
    }
    
    public Vertex(){}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex) {
            Vertex v = (Vertex) obj;
            if (v.info == info) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "name = " + info.toString() + "  d = " + d + "  f = " + f;
    }

    @Override
    public int compareTo(Vertex arg0) {
        if (d > arg0.d) {
            return 1;
        }else if (d < arg0.d) {
            return -1;
        }
        return 0;
    };

    
}
