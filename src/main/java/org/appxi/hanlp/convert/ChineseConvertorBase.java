package org.appxi.hanlp.convert;

import org.appxi.hanlp.util.HanlpHelper;
import org.appxi.hanlp.util.dictionary.StringDictionary;
import org.appxi.hanlp.util.trie.DoubleArrayTrieByAhoCorasick;
import org.appxi.hanlp.util.trie.TrieHelper;
import org.appxi.util.FileHelper;
import org.appxi.util.StringHelper;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

abstract class ChineseConvertorBase extends ChineseConvertor {
    protected static final String pathBase = "appxi/hanlpConvert/data-";

    protected final String id;

    protected ChineseConvertorBase(String id, String name) {
        super(name);
        this.id = id;
    }

    protected Set<String> getReferencedFiles() {
        return null;
    }

    @Override
    protected final void loadDictionaries(DoubleArrayTrieByAhoCorasick<String> trie) {
        final String pathTxt = StringHelper.concat(pathBase, id, ".txt");
        final String pathBin = StringHelper.concat(pathBase, id, ".bin");
        Set<String> pathTxts = getReferencedFiles();
        if (null == pathTxts)
            pathTxts = new HashSet<>();
        pathTxts.add(pathTxt);
        final List<Path> fileTxts = FileHelper.extractFiles(
                file -> getClass().getResourceAsStream("/" + file),
                HanlpHelper::resolveData,
                pathTxts.toArray(new String[0]));

        // load from bin
        final Path fileBin = HanlpHelper.resolveCache(pathBin);
        if (!FileHelper.isTargetFileUpdatable(fileBin, fileTxts.toArray(new Path[0]))) {
            if (TrieHelper.loadObject(fileBin, trie))
                return;
        }
        // load primary
        final TreeMap<String, String> primaryMap = new TreeMap<>();
        final Path fileTxt = HanlpHelper.resolveData(pathTxt);
        if (FileHelper.exists(fileTxt)) {
            final StringDictionary dictionary = new StringDictionary("=");
            dictionary.load(HanlpHelper.ensureStream(fileTxt));
            dictionary.walkEntries(primaryMap::put);
        }
        // load more
        loadMoreDictionaries(primaryMap);
        // build to trie
        long st = System.currentTimeMillis();
        trie.build(primaryMap);
        HanlpHelper.LOG.info("trie.build + " + (System.currentTimeMillis() - st));
        // save to bin
        TrieHelper.saveObject(trie, primaryMap, fileBin);
    }

    protected abstract void loadMoreDictionaries(TreeMap<String, String> primaryMap);

    protected static TreeMap<String, String> loadTxtDictionary(TreeMap<String, String> map, boolean reverse,
                                                               String... fileTxtNames) {
        StringDictionary dictionary = new StringDictionary("=");
        for (String fileTxtName : fileTxtNames) {
            try (InputStream stream = Files.newInputStream(HanlpHelper.resolveData(StringHelper.concat(pathBase, fileTxtName)))) {
                dictionary.load(stream);
            } catch (Exception ignored) {
            }
        }
        if (reverse)
            dictionary = dictionary.reverse();
        dictionary.walkEntries(map::put);
        return map;
    }

    protected static TreeMap<String, String> loadTxtDictionary(boolean reverse, String... fileTxtNames) {
        final TreeMap<String, String> result = new TreeMap<>();
        loadTxtDictionary(result, reverse, fileTxtNames);
        return result;
    }
}
