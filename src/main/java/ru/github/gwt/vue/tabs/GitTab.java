package ru.github.gwt.vue.tabs;

import ru.github.gwt.core.request.GitTreeNode;

public class GitTab {
    public final String name;
    public final String key;
    public final GitTreeNode treeNode;

    public GitTab(String name, String key, GitTreeNode treeNode) {
        this.name = name;
        this.key = key;
        this.treeNode = treeNode;
    }
}
