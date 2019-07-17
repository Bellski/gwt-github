package ru.github.gwt;

import com.axellience.vuegwt.core.client.VueGWT;
import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;

public class GitHubEntryPoint implements EntryPoint {
    @Override
    public void onModuleLoad() {


        DomGlobal.console.warn("vue ready");
    }
}
