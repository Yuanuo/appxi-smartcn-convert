package org.appxi.smartcn.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class HantHK2HantConvertor extends ChineseConvertorBase {
    public static final HantHK2HantConvertor instance = new HantHK2HantConvertor();

    private HantHK2HantConvertor() {
        super("hk2t", "香港繁体转繁体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                pathBase + "t2hk.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, true, "t2hk.txt");
    }
}
