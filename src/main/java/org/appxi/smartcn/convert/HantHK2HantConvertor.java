package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HantHK2HantConvertor extends ChineseConvertorBase {
    public static final HantHK2HantConvertor instance = new HantHK2HantConvertor();

    private HantHK2HantConvertor() {
        super("hk2t", "香港繁体转繁体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-t2hk.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, true, getClass().getResource("data-t2hk.txt"));
    }
}
