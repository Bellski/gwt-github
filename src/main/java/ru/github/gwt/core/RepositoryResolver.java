package ru.github.gwt.core;

import io.reactivex.Single;
import ru.github.gwt.core.request.GitContentRequester;
import ru.github.gwt.core.request.GitRepositoryRequester;
import ru.github.gwt.core.request.GitTreeNodeRequester;

public class RepositoryResolver {

    private final GitRepositoryRequester repositoryRequester;
    private final GitTreeNodeRequester gitTreeNodeRequester;
    private final GitContentRequester gitContentRequester;

    public RepositoryResolver(GitRepositoryRequester repositoryRequester,
                              GitTreeNodeRequester gitTreeNodeRequester,
                              GitContentRequester gitContentRequester) {
        this.repositoryRequester = repositoryRequester;
        this.gitTreeNodeRequester = gitTreeNodeRequester;
        this.gitContentRequester = gitContentRequester;
    }

    public Single<Repository> resolve(String fullName) {
        return repositoryRequester.request(fullName).flatMap(this::createRepository);
    }

    private Single<Repository> createRepository(GitRepositoryRequester.Response response) {
        final String treeUrl = response.trees_url.replace("{/sha}", "/" + response.default_branch);

        return gitTreeNodeRequester.request(treeUrl)
                .map(gitTreeNodes -> new Repository(response.full_name, response.default_branch, gitTreeNodes, gitTreeNodeRequester, gitContentRequester));
    }
}
