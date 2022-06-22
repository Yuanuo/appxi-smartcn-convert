package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HantHK2HansConvertor extends ChineseConvertorBase {
    public static final HantHK2HansConvertor instance = new HantHK2HansConvertor();

    private HantHK2HansConvertor() {
        super("hk2s", "香港繁体转简体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-t2s.txt"),
                getClass().getResource("data-t2hk.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, getClass().getResource("data-t2s.txt"));

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, getClass().getResource("data-t2hk.txt")), true);
    }
}
