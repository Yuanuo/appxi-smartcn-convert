package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HantHK2HantTWConvertor extends ChineseConvertorBase {
    public static final HantHK2HantTWConvertor instance = new HantHK2HantTWConvertor();

    private HantHK2HantTWConvertor() {
        super("hk2tw", "香港繁体转台湾繁体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-t2tw.txt"),
                getClass().getResource("data-t2hk.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, getClass().getResource("data-t2tw.txt"));

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, getClass().getResource("data-t2hk.txt")), false);
    }
}
