package ru.github.gwt.vue;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasCreated;
import jsinterop.annotations.JsMethod;
import ru.github.gwt.Events;
import ru.github.gwt.core.Repository;
import ru.github.gwt.vue.repository.RepositoryTreeComponent;
import ru.github.gwt.vue.tabs.GitTabsComponent;

import javax.inject.Inject;

import static elemental2.dom.DomGlobal.window;

@Component(components = {
        RepositoryTreeComponent.class,
        GitTabsComponent.class
})
public class GitHubComponent implements IsVueComponent, HasCreated {

    @Inject
    public Repository repository;

    @Override
    public void created() {
        window.addEventListener("resize", evt -> vue().$emit(Events.WINDOW_RESIZE));
    }

    @Computed
    public String getOwnerWithName() {
        return repository.getFullName();
    }

    @JsMethod
    public void onDragGutter() {
        vue().$emit(Events.DRAG_GUTTER);
    }
}
