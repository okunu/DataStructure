package com.okunu.graph;

public class Vertex {
    public static enum COLOR{WHITE, BLACK, GRAY};
    
    public Object info;
    public int d;
    public int f;
    public COLOR color;
    public Arc firstArc;
    
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
    };

    
}
