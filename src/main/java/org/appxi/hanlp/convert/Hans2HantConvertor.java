package org.appxi.hanlp.convert;

import java.util.TreeMap;

public class Hans2HantConvertor extends ChineseConvertorBase {
    public static final Hans2HantConvertor instance = new Hans2HantConvertor();

    private Hans2HantConvertor() {
        super("s2t", "简繁转换");
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
    }
}
