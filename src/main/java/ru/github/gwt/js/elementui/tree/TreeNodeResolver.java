package ru.github.gwt.js.elementui.tree;

import jsinterop.annotations.JsFunction;

@JsFunction
@FunctionalInterface
public interface TreeNodeResolver<T> {
    Object resolve(T[] nodes);
}