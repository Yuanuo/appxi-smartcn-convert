package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HantTW2HantConvertor extends ChineseConvertorBase {
    public static final HantTW2HantConvertor instance = new HantTW2HantConvertor();

    private HantTW2HantConvertor() {
        super("tw2t", "台湾繁体转繁体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-t2tw.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, true, getClass().getResource("data-t2tw.txt"));
    }
}
