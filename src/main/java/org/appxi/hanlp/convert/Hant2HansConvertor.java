package org.appxi.hanlp.convert;

import java.util.TreeMap;

public class Hant2HansConvertor extends ChineseConvertorBase {
    public static final Hant2HansConvertor instance = new Hant2HansConvertor();

    private Hant2HansConvertor() {
        super("t2s", "繁简转换");
    }

    @Override
    protected void loadMoreDictionaries(TreeMap<String, String> primaryMap) {
    }
}
