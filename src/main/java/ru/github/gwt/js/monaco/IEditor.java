package ru.github.gwt.js.monaco;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class IEditor implements Disposable{
    public native void layout();
    public native ITextModel getModel();

    @Override
    public native void dispose();
}
