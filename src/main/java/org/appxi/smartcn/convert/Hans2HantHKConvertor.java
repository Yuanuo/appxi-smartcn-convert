package org.appxi.smartcn.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Hans2HantHKConvertor extends ChineseConvertorBase {
    public static final Hans2HantHKConvertor instance = new Hans2HantHKConvertor();

    private Hans2HantHKConvertor() {
        super("s2hk", "简体转香港繁体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                "data-s2t.txt",
                "data-t2hk.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "data-s2t.txt");

        CombineUtil.combine(primaryMap, loadTxtDictionary(false, "data-t2hk.txt"));
    }
}
