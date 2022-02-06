package org.appxi.smartcn.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Hant2HantTWConvertor extends ChineseConvertorBase {
    public static final Hant2HantTWConvertor instance = new Hant2HantTWConvertor();

    private Hant2HantTWConvertor() {
        super("t2tw", "繁体转台湾繁体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                "data-t2tw.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "data-t2tw.txt");
    }
}
