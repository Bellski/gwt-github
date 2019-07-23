package ru.github.gwt.vue.repository;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasCreated;
import jsinterop.annotations.JsMethod;
import ru.github.gwt.core.request.GitTreeNode;
import ru.github.gwt.core.Repository;
import ru.github.gwt.vue.RepositoryTreeNode;
import ru.github.gwt.js.elementui.tree.TreeNode;
import ru.github.gwt.js.elementui.tree.TreeNodeResolver;
import ru.github.gwt.vue.tabs.GitTabsSharedState;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Comparator;

@Component
public class RepositoryTreeComponent implements IsVueComponent, HasCreated {

    @Inject
    public GitTabsSharedState state;

    @Inject
    public Repository repository;

    @Override
    public void created() {

    }

    @JsMethod
    public void resolveTreeNodes(TreeNode<RepositoryTreeNode> treeNode, TreeNodeResolver<RepositoryTreeNode> treeNodeResolver) {
        if (treeNode.data == null) {
            treeNodeResolver.resolve(mapGitTreeNodes(null, repository.getRootNodes()));


        } else {
            repository
                    .resolveGitTree(treeNode.data.getTreeNode())
                    .subscribe(gitTreeNodes -> treeNodeResolver.resolve(mapGitTreeNodes(treeNode.data.getName(), gitTreeNodes)));
        }

        treeNode.expanded = true;
    }

    private RepositoryTreeNode[] mapGitTreeNodes(String parent, GitTreeNode[] gitTreeNodes) {
        return Arrays
                .stream(gitTreeNodes)
                .map(treeNode -> new RepositoryTreeNode(treeNode, parent))
                .sorted(Comparator.comparing(repositoryTreeNode -> repositoryTreeNode.leaf))
                .toArray(RepositoryTreeNode[]::new);
    }

    @JsMethod
    public void onNodeClick(RepositoryTreeNode repositoryTreeNode) {
        if (repositoryTreeNode.leaf) {
            state.addGitTab(repositoryTreeNode.getName(), repositoryTreeNode.getPath(), repositoryTreeNode.getTreeNode());
        }
    }
}
