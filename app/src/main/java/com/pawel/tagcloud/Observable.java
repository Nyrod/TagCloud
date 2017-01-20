package com.pawel.tagcloud;

import java.util.ArrayList;

/**
 * Created by Pawel on 20.01.2017.
 */

public class Observable<T> {

    private final ArrayList<OnChangeListener<T>> listenersList = new ArrayList<>();

    public void addListener(OnChangeListener listener) {
        listenersList.add(listener);
    }
    public void removeListener(OnChangeListener listener) {
        listenersList.remove(listener);
    }

    protected void notifyObservers(final T model) {
        synchronized (listenersList) {
            for (OnChangeListener listener : listenersList) {
                listener.onChange(model);
            }
        }
    }
}
