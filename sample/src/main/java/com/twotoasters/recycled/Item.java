package com.twotoasters.recycled;

import java.io.Serializable;

public class Item implements Serializable {
    private final String mName;

    public Item(String name) {
        this.mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }
}
