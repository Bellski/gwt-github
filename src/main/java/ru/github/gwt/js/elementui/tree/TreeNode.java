package ru.github.gwt.js.elementui.tree;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class TreeNode<T> {
    public T data;
    public boolean expanded;
    public boolean isLeaf;
    public String key;
}
