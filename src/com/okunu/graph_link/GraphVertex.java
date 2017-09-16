package com.okunu.graph_link;

public class GraphVertex {
    public enum COLOR{WHITE, BLACK, GRAY};
    
    public String name;
    public int d;
    public GraphVertex pre;
    public COLOR color;
    
    public GraphVertex(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getD() {
        return d;
    }
    public void setD(int d) {
        this.d = d;
    }
    public GraphVertex getPre() {
        return pre;
    }
    public void setPre(GraphVertex pre) {
        this.pre = pre;
    }
    public COLOR getColor() {
        return color;
    }
    public void setColor(COLOR color) {
        this.color = color;
    }

    
}
