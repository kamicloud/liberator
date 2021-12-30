package com.github.kamicloud.liberator.stubs.core;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class EnumStub extends Stub {
    protected final LinkedHashMap<String, EnumStubItem> items = new LinkedHashMap<>();

    public EnumStub(String name, String classpath) {
        super(name, classpath, null);
    }

    public void addItem(String key, String classpath, String value, EnumStubItemType type) {
        items.put(key, new EnumStubItem(value, classpath, type));
    }

    public void addItem(String key, EnumStubItem enumStubItem) {
        items.put(key, enumStubItem);
    }

    public HashMap<String, EnumStubItem> getItems() {
        return items;
    }

    public static class EnumStubItem extends Stub {
        protected final EnumStubItemType type;
        public EnumStubItem(String name, String classpath, EnumStubItemType type) {
            super(name, classpath, null);
            this.type = type;
        }

        public EnumStubItemType getType() {
            return type;
        }
    }

    public enum EnumStubItemType {
        INTEGER,
        STRING,
        EXPRESSION,
    }
}
