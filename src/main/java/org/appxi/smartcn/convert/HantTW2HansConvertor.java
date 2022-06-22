package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HantTW2HansConvertor extends ChineseConvertorBase {
    public static final HantTW2HansConvertor instance = new HantTW2HansConvertor();

    private HantTW2HansConvertor() {
        super("tw2s", "台湾繁体转简体");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return Arrays.asList(
                getClass().getResource("data-t2s.txt"),
                getClass().getResource("data-t2tw.txt")
        );
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
        loadTxtDictionary(primaryMap, false, getClass().getResource("data-t2s.txt"));

        CombineUtil.combineReverse(primaryMap, loadTxtDictionary(true, getClass().getResource("data-t2tw.txt")), true);
    }
}
