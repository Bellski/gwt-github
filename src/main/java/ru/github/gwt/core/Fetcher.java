package ru.github.gwt.core;

import elemental2.dom.DomGlobal;
import elemental2.promise.Promise;
import io.reactivex.Single;
import jsinterop.base.Js;

import static com.intendia.rxgwt2.elemental2.RxElemental2.fromPromise;

public class Fetcher {

    private Fetcher() {
    }

    public static <T> Single<T> fetch(String url) {
        return fromPromise(DomGlobal.fetch(url))
                .doOnSuccess(response -> {
                    if (response.status == 404) {
                        throw new IllegalStateException("Resource not found");
                    }
                })
                .flatMap(response -> fromPromise(Js.<Promise<T>>cast(response.json())));
    }
}