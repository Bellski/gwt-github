package ru.github.gwt.dagger;

import dagger.BindsInstance;
import dagger.Component;
import ru.github.gwt.core.Repository;
import ru.github.gwt.vue.GitHubComponentFactory;

import javax.inject.Singleton;

@Component
@Singleton
public interface GitHubDaggerComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        GitHubDaggerComponent.Builder repository(Repository repository);
        GitHubDaggerComponent build();
    }

    GitHubComponentFactory gitHubComponentFactory();
}
