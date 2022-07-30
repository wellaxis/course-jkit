package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.iterator;

/**
 * Desc: Name Repository class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class NameRepository implements Container {
    public String[] names = {"Robert", "John", "Julie", "Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public String next() {
            if (hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}
