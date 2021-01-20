package org.appxi.hanlp.convert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class HantTW2HantConvertor extends ChineseConvertorBase {
    public static final HantTW2HantConvertor instance = new HantTW2HantConvertor();

    private HantTW2HantConvertor() {
        super("tw2t", "台湾繁体转繁体");
    }

    @Override
    protected Set<String> getReferencedFiles() {
        return new HashSet<>(Arrays.asList(
                pathBase + "t2tw.txt"
        ));
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, true, "t2tw.txt");
    }
}
