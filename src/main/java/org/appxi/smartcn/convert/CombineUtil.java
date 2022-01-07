package org.appxi.smartcn.convert;

import org.appxi.smartcn.chars.StandardChars;

import java.util.Map;
import java.util.TreeMap;

final class CombineUtil {

    static void combine(TreeMap<String, String> s2t, TreeMap<String, String> t2x) {
        for (Map.Entry<String, String> entry : s2t.entrySet()) {
            String x = t2x.get(entry.getValue());
            if (x != null) {
                entry.setValue(x);
            }
        }
        for (Map.Entry<String, String> entry : t2x.entrySet()) {
            String s = StandardChars.convert(entry.getKey());
            if (!s2t.containsKey(s)) {
                s2t.put(s, entry.getValue());
            }
        }
    }

    static void combineReverse(TreeMap<String, String> t2s, TreeMap<String, String> tw2t, boolean convert) {
        for (Map.Entry<String, String> entry : tw2t.entrySet()) {
            String tw = entry.getKey();
            String s = t2s.get(entry.getValue());
            if (s == null)
                s = convert ? StandardChars.convert(entry.getValue()) : entry.getValue();
            t2s.put(tw, s);
        }
    }
}
