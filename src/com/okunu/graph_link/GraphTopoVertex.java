package com.okunu.graph_link;

public class GraphTopoVertex extends GraphVertex{

    public GraphTopoVertex(String name) {
        super(name);
    }
    
    public int f;
    public GraphTopoVertex[] next;

    public GraphTopoVertex[] getNext() {
        return next;
    }

    public void setNext(GraphTopoVertex[] next) {
        this.next = next;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return name + "  d = " + d + "  f = " + f;
    }

}
