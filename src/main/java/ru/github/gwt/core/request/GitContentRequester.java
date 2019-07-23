package ru.github.gwt.core.request;

import io.reactivex.Single;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import ru.github.gwt.core.Fetcher;

public class GitContentRequester {
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Response {
        public String content;
    }

    public Single<String> request(String treeUrl) {
        return Fetcher.<Response>fetch(treeUrl).map(response -> response.content);
    }
}
