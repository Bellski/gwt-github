package ru.github.gwt.vue;

import jsinterop.annotations.JsProperty;
import ru.github.gwt.core.request.GitTreeNode;

public class RepositoryTreeNode {

    @JsProperty
    public boolean leaf;

    private final String path;

    private final GitTreeNode treeNode;

    public RepositoryTreeNode(GitTreeNode treeNode, String parent) {
        this.treeNode = treeNode;
        this.leaf = treeNode.type.equals("blob");
        this.path = parent != null ? parent + "/" + treeNode.path : treeNode.path;
    }

    public String getName() {
        return treeNode.path;
    }

    public String getPath() {
        return path;
    }

    public GitTreeNode getTreeNode() {
        return treeNode;
    }
}
