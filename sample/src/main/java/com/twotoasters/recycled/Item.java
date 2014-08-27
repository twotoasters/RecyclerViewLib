package com.twotoasters.recycled;

public class Item {
    private final String mName;

    public Item(String name) {
        this.mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }
}
