package org.appxi.smartcn.convert;


import org.appxi.smartcn.util.trie.AbstractDictionaryTrieApp;

public abstract class ChineseConvertor extends AbstractDictionaryTrieApp<String> {
    public final String name;

    public ChineseConvertor(String name) {
        this.name = name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public final String convert(String string) {
        return convert(string.toCharArray());
    }

    public final String convert(char... chars) {
        final String[] wordNet = new String[chars.length];
        final int[] lengthNet = new int[chars.length];
        getDictionaryTrie().parseText(chars, (begin, end, value) -> {
            int length = end - begin;
            if (length > lengthNet[begin]) {
                wordNet[begin] = value;
                lengthNet[begin] = length;
            }
        });
        final StringBuilder result = new StringBuilder(chars.length);
        for (int offset = 0; offset < wordNet.length; ) {
            if (wordNet[offset] == null) {
                result.append(chars[offset]);
                ++offset;
                continue;
            }
            result.append(wordNet[offset]);
            offset += lengthNet[offset];
        }
        return result.toString();
    }
}
