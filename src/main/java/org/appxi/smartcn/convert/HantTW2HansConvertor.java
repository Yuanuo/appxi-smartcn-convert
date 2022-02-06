package org.appxi.smartcn.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class HantTW2HansConvertor extends ChineseConvertorBase {
    public static final HantTW2HansConvertor instance = new HantTW2HansConvertor();

    private HantTW2HansConvertor() {
        super("tw2s", "台湾繁体转简体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                "data-t2s.txt",
                "data-t2tw.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, "data-t2s.txt");

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, "data-t2tw.txt"), true);
    }
}
