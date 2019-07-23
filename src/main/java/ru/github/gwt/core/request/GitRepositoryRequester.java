package ru.github.gwt.core.request;

import io.reactivex.Single;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import ru.github.gwt.core.Fetcher;

public class GitRepositoryRequester {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Response {
        public String full_name;
        public String default_branch;
        public String trees_url;
    }

    public Single<Response> request(String fullName) {
        return Fetcher.fetch("https://api.github.com/repos/" + fullName);
    }
}
