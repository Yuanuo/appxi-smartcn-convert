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
                pathBase + "t2hk.txt",
                pathBase + "t2tw.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "t2hk.txt");

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, "t2tw.txt"), false);
    }
}
