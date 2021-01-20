package org.appxi.hanlp.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class HantHK2HansConvertor extends ChineseConvertorBase {
    public static final HantHK2HansConvertor instance = new HantHK2HansConvertor();

    private HantHK2HansConvertor() {
        super("hk2s", "香港繁体转简体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                pathBase + "t2s.txt",
                pathBase + "t2hk.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "t2s.txt");

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, "t2hk.txt"), true);
    }
}
