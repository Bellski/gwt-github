package ru.github.gwt.js;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;

public class GitHubGlobal {

    @JsMethod(namespace = JsPackage.GLOBAL)
    public static native String atob(String content);
}
