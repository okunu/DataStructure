package com.okunu.tree;

import com.okunu.basic.Node;

public class ClueNode extends Node{

    public ClueNode() {
        super();
    }

    public ClueNode(String str) {
        super(str);
    }
    
    public enum TAG{LINK, THREAD};
    
    private TAG mLeftTag = TAG.LINK;
    
    private TAG mRightTag = TAG.LINK;

    public TAG getLeftTag() {
        return mLeftTag;
    }

    public void setLeftTag(TAG leftTag) {
        this.mLeftTag = leftTag;
    }

    public TAG getRightTag() {
        return mRightTag;
    }

    public void setRightTag(TAG rightTag) {
        this.mRightTag = rightTag;
    }
    
    
}
