package org.appxi.smartcn.convert;

import java.net.URL;
import java.util.List;
import java.util.TreeMap;

public class Hans2HantConvertor extends ChineseConvertorBase {
    public static final Hans2HantConvertor instance = new Hans2HantConvertor();

    private Hans2HantConvertor() {
        super("s2t", "简繁转换");
    }

    @Override
    protected List<URL> getDependencyTxtSources() {
        return List.of();
    }

    @Override
    protected void loadDependencyTxtSources(TreeMap<String, String> primaryMap) {
    }
}
