package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HantTW2HantHKConvertor extends ChineseConvertorBase {
    public static final HantTW2HantHKConvertor instance = new HantTW2HantHKConvertor();

    private HantTW2HantHKConvertor() {
        super("tw2hk", "台湾繁体转香港繁体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-t2hk.txt"),
                getClass().getResource("data-t2tw.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, getClass().getResource("data-t2hk.txt"));

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, getClass().getResource("data-t2tw.txt")), false);
    }
}
