package org.appxi.smartcn.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class HantHK2HantTWConvertor extends ChineseConvertorBase {
    public static final HantHK2HantTWConvertor instance = new HantHK2HantTWConvertor();

    private HantHK2HantTWConvertor() {
        super("hk2tw", "香港繁体转台湾繁体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                pathBase + "t2tw.txt",
                pathBase + "t2hk.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "t2tw.txt");

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, "t2hk.txt"), false);
    }
}
