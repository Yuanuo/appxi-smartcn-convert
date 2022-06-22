package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.List;
import java.util.TreeMap;

public class Hant2HansConvertor extends ChineseConvertorBase {
    public static final Hant2HansConvertor instance = new Hant2HansConvertor();

    private Hant2HansConvertor() {
        super("t2s", "繁简转换");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return List.of();
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
    }
}
