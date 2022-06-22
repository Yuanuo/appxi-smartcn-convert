package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Hant2HantHKConvertor extends ChineseConvertorBase {
    public static final Hant2HantHKConvertor instance = new Hant2HantHKConvertor();

    private Hant2HantHKConvertor() {
        super("t2hk", "繁体转香港繁体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-t2hk.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, getClass().getResource("data-t2hk.txt"));
    }
}
