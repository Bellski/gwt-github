package ru.github.gwt.core;

import io.reactivex.Single;
import ru.github.gwt.core.request.GitContentRequester;
import ru.github.gwt.core.request.GitTreeNode;
import ru.github.gwt.core.request.GitTreeNodeRequester;
import ru.github.gwt.js.GitHubGlobal;

public class Repository {

    private final String fullName;
    private final String branch;
    private final GitTreeNode[] rootNodes;

    private final GitTreeNodeRequester gitTreeNodeRequester;
    private final GitContentRequester gitContentRequester;

    public Repository(String fullName, String branch, GitTreeNode[] rootNodes, GitTreeNodeRequester gitTreeNodeRequester, GitContentRequester gitContentRequester) {
        this.fullName = fullName;
        this.branch = branch;
        this.rootNodes = rootNodes;
        this.gitTreeNodeRequester = gitTreeNodeRequester;
        this.gitContentRequester = gitContentRequester;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBranch() {
        return branch;
    }

    public GitTreeNode[] getRootNodes() {
        return rootNodes;
    }

    public Single<GitTreeNode[]> resolveGitTree(GitTreeNode treeNode) {
        return gitTreeNodeRequester.request(treeNode.url);
    }

    public Single<String> resolveGitContent(GitTreeNode treeNode) {
        return gitContentRequester.request(treeNode.url).map(GitHubGlobal::atob);
    }
}
