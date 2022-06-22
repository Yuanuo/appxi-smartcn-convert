package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Hant2HantTWConvertor extends ChineseConvertorBase {
    public static final Hant2HantTWConvertor instance = new Hant2HantTWConvertor();

    private Hant2HantTWConvertor() {
        super("t2tw", "繁体转台湾繁体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-t2tw.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, getClass().getResource("data-t2tw.txt"));
    }
}
