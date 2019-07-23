package ru.github.gwt.vue.tabs;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import elemental2.core.JsArray;
import jsinterop.annotations.JsMethod;
import ru.github.gwt.vue.tabs.gitview.GitSourceViewComponent;

import javax.inject.Inject;

@Component(components = {
        GitSourceViewComponent.class
})
public class GitTabsComponent implements IsVueComponent {

    @Data
    @Inject
    public GitTabsSharedState state;

    @Computed
    public JsArray<GitTab> getTabs() {
        return state.tabs;
    }

    @Computed
    public String getActiveTab() {
        return state.activeCodeTab;
    }

    @Computed
    public void setActiveTab(String activeTab) {
        state.activeCodeTab = activeTab;
    }

    @Computed
    public boolean getHasTabs() {
        return state.tabs.length > 0;
    }

    @JsMethod
    public void onTabRemove(String name) {
        state.removeGitTab(name);
    }
}
