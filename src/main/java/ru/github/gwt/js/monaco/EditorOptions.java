package ru.github.gwt.js.monaco;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class EditorOptions {
    public ITextModel model;

    @JsOverlay
    public static EditorOptions create(ITextModel model) {
        final EditorOptions editorOptions = new EditorOptions();
        editorOptions.model = model;

        return editorOptions;
    }
}
