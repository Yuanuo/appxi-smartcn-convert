package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Hans2HantHKConvertor extends ChineseConvertorBase {
    public static final Hans2HantHKConvertor instance = new Hans2HantHKConvertor();

    private Hans2HantHKConvertor() {
        super("s2hk", "简体转香港繁体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-s2t.txt"),
                getClass().getResource("data-t2hk.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, getClass().getResource("data-s2t.txt"));

        CombineUtil.combine(primaryMap, loadTxtDictionary(false, getClass().getResource("data-t2hk.txt")));
    }
}
