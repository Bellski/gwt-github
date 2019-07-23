package ru.github.gwt.js.monaco;

import elemental2.dom.Element;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "monaco")
public class Monaco {

    @JsMethod(name = "editor.create")
    public static native IEditor createEditor(Element element, EditorOptions options);

    @JsMethod(name = "editor.createModel")
    public static native ITextModel createModel(String text, String languageId);

    @JsMethod(name = "languages.getLanguages")
    public static native ILanguageExtensionPoint[] getLanguages();
}
