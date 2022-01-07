package org.appxi.smartcn.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Hant2HantHKConvertor extends ChineseConvertorBase {
    public static final Hant2HantHKConvertor instance = new Hant2HantHKConvertor();

    private Hant2HantHKConvertor() {
        super("t2hk", "繁体转香港繁体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                pathBase + "t2hk.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "t2hk.txt");
    }
}
