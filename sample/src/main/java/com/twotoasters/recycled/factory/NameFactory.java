package com.twotoasters.recycled.factory;

import com.twotoasters.recycled.Item;

import java.util.ArrayList;
import java.util.Random;

public final class NameFactory {

    private final static String[] ANDROID_NAMES = new String[] { "Ben", "Chris", "Curtis", "Fred", "James", "Pat", "Marcelle" };
    private final static String[] TWO_TOASTERS_NAMES = new String[] { "Ben", "Chris", "Curtis", "Fred"
            , "James", "Pat", "Rachit", "Adit", "Matt", "Josh", "Kayla", "Simon", "Joe", "Harold", "Danie"
            , "Dustin", "Darwin", "Karl", "Jason", "Kevin", "Prachi", "Duncan", "Tom", "Steve", "Ameir", "Jill"};

    private NameFactory() { }

    public static ArrayList<Item> getListOfNames() {
        ArrayList<Item> list = new ArrayList<Item>();
        for (String name : ANDROID_NAMES) {
            list.add(new Item(name));
        }
        return list;
    }

    public static Item getRandomName() {
        String name = TWO_TOASTERS_NAMES[new Random().nextInt(TWO_TOASTERS_NAMES.length)];
        return new Item(name);
    }
}
