package org.appxi.smartcn.convert;

import org.appxi.util.ext.HanLang;

public interface ChineseConvertors {
    /**
     * 繁转简
     *
     * @param text 繁体中文
     * @return 简体中文
     */
    static String toHans(String text) {
        return Hant2HansConvertor.instance.convert(text.toCharArray());
    }

    /**
     * 简转繁
     *
     * @param text 简体中文
     * @return 繁体中文
     */
    static String toHant(String text) {
        return Hans2HantConvertor.instance.convert(text.toCharArray());
    }

    /**
     * 简转繁,是{@link ChineseConvertors#toHant(String)}的简称
     *
     * @param text 简体中文
     * @return 繁体中文(大陆标准)
     */
    static String hans2Hant(String text) {
        return toHant(text);
    }

    /**
     * 繁转简,是{@link ChineseConvertors#toHans(String)}的简称
     *
     * @param text 繁体中文(大陆标准)
     * @return 简体中文
     */
    static String hant2Hans(String text) {
        return toHans(text);
    }

    /**
     * 簡體到臺灣正體
     *
     * @param text 簡體
     * @return 臺灣正體
     */
    static String hans2HantTW(String text) {
        return Hans2HantTWConvertor.instance.convert(text);
    }

    /**
     * 臺灣正體到簡體
     *
     * @param text 臺灣正體
     * @return 簡體
     */
    static String hantTW2Hans(String text) {
        return HantTW2HansConvertor.instance.convert(text);
    }

    /**
     * 簡體到香港繁體
     *
     * @param text 簡體
     * @return 香港繁體
     */
    static String hans2HantHK(String text) {
        return Hans2HantHKConvertor.instance.convert(text);
    }

    /**
     * 香港繁體到簡體
     *
     * @param text 香港繁體
     * @return 簡體
     */
    static String hantHK2Hans(String text) {
        return HantHK2HansConvertor.instance.convert(text);
    }

    /**
     * 繁體到臺灣正體
     *
     * @param text 繁體
     * @return 臺灣正體
     */
    static String hant2HantTW(String text) {
        return Hant2HantTWConvertor.instance.convert(text);
    }

    /**
     * 臺灣正體到繁體
     *
     * @param text 臺灣正體
     * @return 繁體
     */
    static String hantTW2Hant(String text) {
        return HantTW2HantConvertor.instance.convert(text);
    }

    /**
     * 繁體到香港繁體
     *
     * @param text 繁體
     * @return 香港繁體
     */
    static String hant2HantHK(String text) {
        return Hant2HantHKConvertor.instance.convert(text);
    }

    /**
     * 香港繁體到繁體
     *
     * @param text 香港繁體
     * @return 繁體
     */
    static String hantHK2Hant(String text) {
        return HantHK2HantConvertor.instance.convert(text);
    }

    /**
     * 香港繁體到臺灣正體
     *
     * @param text 香港繁體
     * @return 臺灣正體
     */
    static String hantHK2HantTW(String text) {
        return HantHK2HantTWConvertor.instance.convert(text);
    }

    /**
     * 臺灣正體到香港繁體
     *
     * @param text 臺灣正體
     * @return 香港繁體
     */
    static String hantTW2HantHK(String text) {
        return HantTW2HantHKConvertor.instance.convert(text);
    }

    static String convert(String text, HanLang sourceHan, HanLang targetHan) {
        if (sourceHan == targetHan || null == targetHan)
            return text;
        if (null == sourceHan) {
            return switch (targetHan) {
                case zh, hans, hansCN -> toHans(text);
                case hant -> toHant(text);
                case hantTW -> hans2HantTW(text);
                case hantHK -> hans2HantHK(text);
                case hantMO, hantSG -> hans2Hant(text);
            };
        }
        switch (sourceHan) {
            case zh, hans, hansCN -> {
                return switch (targetHan) {
                    case zh, hans, hansCN -> String.valueOf(text);
                    case hant, hantMO, hantSG -> hans2Hant(text);
                    case hantTW -> hans2HantTW(text);
                    case hantHK -> hans2HantHK(text);
                };
            }
            case hant, hantMO, hantSG -> {
                return switch (targetHan) {
                    case zh, hans, hansCN -> hant2Hans(text);
                    case hant, hantMO, hantSG -> String.valueOf(text);
                    case hantTW -> hant2HantTW(text);
                    case hantHK -> hant2HantHK(text);
                };
            }
            case hantTW -> {
                return switch (targetHan) {
                    case zh, hans, hansCN -> hantTW2Hans(text);
                    case hant, hantMO, hantSG -> hantTW2Hant(text);
                    case hantTW -> String.valueOf(text);
                    case hantHK -> hantTW2HantHK(text);
                };
            }
            case hantHK -> {
                return switch (targetHan) {
                    case zh, hans, hansCN -> hantHK2Hans(text);
                    case hant, hantMO, hantSG -> hantHK2Hant(text);
                    case hantTW -> hantHK2HantTW(text);
                    case hantHK -> String.valueOf(text);
                };
            }
        }
        return text;
    }
}
