package ru.github.gwt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import elemental2.dom.HTMLElement;
import io.reactivex.plugins.RxJavaPlugins;
import jsinterop.base.Js;
import ru.github.gwt.core.Repository;
import ru.github.gwt.core.RepositoryResolver;
import ru.github.gwt.core.request.GitContentRequester;
import ru.github.gwt.core.request.GitTreeNodeRequester;
import ru.github.gwt.core.request.GitRepositoryRequester;
import ru.github.gwt.dagger.DaggerGitHubDaggerComponent;

import static elemental2.dom.DomGlobal.document;
import static elemental2.dom.DomGlobal.window;

public class GitHubEntryPoint implements EntryPoint {
    @Override
    public void onModuleLoad() {
        RxJavaPlugins.setErrorHandler(throwable -> GWT.log("", throwable));

        final String path = window.location.getPathname().substring(1);

        final HTMLElement resolverElement = createResolverElement();

        final RepositoryResolver repositoryResolver = new RepositoryResolver(
                new GitRepositoryRequester(),
                new GitTreeNodeRequester(),
                new GitContentRequester()
        );

        repositoryResolver.resolve(path).subscribe(this::openRepository, throwable -> {
            resolverElement.textContent = throwable.getMessage();

            GWT.log("", throwable);
        });
    }


    private HTMLElement createResolverElement() {
        final HTMLElement resolveElement = Js.cast(document.createElement("div"));
        resolveElement.className = "resolve";
        resolveElement.innerHTML = "Resolving repository...";

        document.querySelector("#app").appendChild(resolveElement);

        return resolveElement;
    }

    private void openRepository(Repository repository) {
        DaggerGitHubDaggerComponent
                .builder()
                .repository(repository)
                .build()
                .gitHubComponentFactory()
                .create()
                .vue()
                .$mount("#app");
    }
}
