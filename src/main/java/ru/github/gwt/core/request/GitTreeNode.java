package ru.github.gwt.core.request;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class GitTreeNode {
    public String path;
    public String type;
    public String url;
}