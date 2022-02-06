package org.appxi.smartcn.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Hans2HantTWConvertor extends ChineseConvertorBase {
    public static final Hans2HantTWConvertor instance = new Hans2HantTWConvertor();

    private Hans2HantTWConvertor() {
        super("s2tw", "简体转台湾繁体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                "data-s2t.txt",
                "data-t2tw.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "data-s2t.txt");

        CombineUtil.combine(primaryMap, loadTxtDictionary(false, "data-t2tw.txt"));
    }
}
