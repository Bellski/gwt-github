package ru.github.gwt.vue.tabs;

import elemental2.core.JsArray;
import jsinterop.base.Js;
import ru.github.gwt.core.request.GitTreeNode;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GitTabsSharedState {
    public JsArray<GitTab> tabs = new JsArray<>();
    public String activeCodeTab = null;

    @Inject
    public GitTabsSharedState() {
    }

    public void addGitTab(String name, String path, GitTreeNode treeNode) {
        final GitTab gitTab = tabs.find((p0, p1, p2) -> p0.key.equals(path));

        if (gitTab == null) {
            tabs.push(new GitTab(name, path, treeNode));
        }

        activeCodeTab = path;
    }

    public void removeGitTab(String name) {
        if (tabs.length == 1) {
            tabs = new JsArray<>();
            activeCodeTab = null;

            return;
        }

        if (activeCodeTab.equals(name)) {
            final int indexOfTab = tabs.indexOf(tabs.find((p0, p1, p2) -> p0.key.equals(name)));

            activeCodeTab = tabs.getAt(indexOfTab > 0 ? indexOfTab - 1 : indexOfTab + 1).key;
        }

        tabs = new JsArray<>(Js.uncheckedCast(tabs.filter((p0, p1, p2) -> !p0.key.equals(name))));
    }
}
