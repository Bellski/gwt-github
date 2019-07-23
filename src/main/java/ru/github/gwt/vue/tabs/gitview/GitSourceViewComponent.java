package ru.github.gwt.vue.tabs.gitview;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.annotations.component.Ref;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasBeforeDestroy;
import com.axellience.vuegwt.core.client.component.hooks.HasMounted;
import com.axellience.vuegwt.core.client.component.options.functions.OnEvent;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;
import ru.github.gwt.Events;
import ru.github.gwt.core.Repository;
import ru.github.gwt.js.monaco.EditorOptions;
import ru.github.gwt.js.monaco.IEditor;
import ru.github.gwt.js.monaco.LanguageExtensionPoints;
import ru.github.gwt.vue.tabs.GitTab;
import ru.github.gwt.vue.tabs.GitTabsSharedState;

import javax.inject.Inject;

import static ru.github.gwt.js.monaco.Monaco.createEditor;
import static ru.github.gwt.js.monaco.Monaco.createModel;

@Component
public class GitSourceViewComponent implements IsVueComponent, HasMounted, HasBeforeDestroy {

    @Inject
    public Repository repository;

    @Inject
    public LanguageExtensionPoints languageExtensionPoints;

    @Inject
    public GitTabsSharedState gitTabsSharedState;

    @Prop
    public GitTab gitTab;

    @Data
    public boolean loading = true;

    @Ref
    public HTMLElement monacoContainer;

    private IEditor editor;

    private OnEvent onWindowResize;

    @Override
    public void mounted() {
        vue().$watch(() -> gitTabsSharedState.activeCodeTab, (newActiveTab, oldActiveTab) -> {
            if (gitTab.key.equals(newActiveTab)) {
                resizeEditor();
            }
        });

        vue().$root().vue().$on(Events.WINDOW_RESIZE, onWindowResize = parameter -> resizeEditor());
        vue().$root().vue().$on(Events.DRAG_GUTTER, onWindowResize = parameter -> resizeEditor());

        repository.resolveGitContent(gitTab.treeNode).subscribe(content -> {
            loading = false;

            vue().$nextTick(() -> {
                editor = createEditor(monacoContainer, EditorOptions.create(createModel(content, languageExtensionPoints.getLanguageFromFileName(gitTab.name))));
                editor.layout();
            });
        });
    }

    private void resizeEditor() {
        if (editor != null) {
            editor.layout();
        }
    }

    @Override
    public void beforeDestroy() {
        vue().$root().vue().$off(Events.WINDOW_RESIZE, onWindowResize);

        if (editor != null) {
            editor.getModel().dispose();
            editor.dispose();
        }
    }
}
