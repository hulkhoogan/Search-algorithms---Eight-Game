package com.eightgamesolver.common;

public class Node {
    private int depth;
    private String path = "";

    public Node(int depth, String move) {
        this.depth = depth;
        this.path = this.path + move;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
