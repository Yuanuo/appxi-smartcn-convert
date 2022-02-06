package org.appxi.smartcn.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class HantTW2HantHKConvertor extends ChineseConvertorBase {
    public static final HantTW2HantHKConvertor instance = new HantTW2HantHKConvertor();

    private HantTW2HantHKConvertor() {
        super("tw2hk", "台湾繁体转香港繁体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                "data-t2hk.txt",
                "data-t2tw.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "data-t2hk.txt");

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, "data-t2tw.txt"), false);
    }
}
