package org.appxi.smartcn.convert;

import org.appxi.smartcn.util.SmartCNHelper;
import org.appxi.smartcn.util.dictionary.StringDictionary;
import org.appxi.smartcn.util.trie.DoubleArrayTrieByAhoCorasick;
import org.appxi.smartcn.util.trie.TrieHelper;
import org.appxi.util.FileHelper;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

abstract class ChineseConvertorBase extends ChineseConvertor {
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
        final String pathTxt = "data-".concat(id).concat(".txt");
        final String pathBin = "data-".concat(id).concat(".bin");
        Set<String> pathTxts = getReferencedFiles();
        if (null == pathTxts)
            pathTxts = new HashSet<>();
        pathTxts.add(pathTxt);
        final List<Path> fileTxts = FileHelper.extractFiles(
                file -> getClass().getResourceAsStream(file),
                other -> SmartCNHelper.resolveData("convert").resolve(other),
                pathTxts.toArray(new String[0]));

        // load from bin
        final Path fileBin = SmartCNHelper.resolveCache("convert").resolve(pathBin);
        if (!FileHelper.isTargetFileUpdatable(fileBin, fileTxts.toArray(new Path[0]))) {
            if (TrieHelper.loadObject(fileBin, trie))
                return;
        }
        // load primary
        final TreeMap<String, String> primaryMap = new TreeMap<>();
        final Path fileTxt = SmartCNHelper.resolveData("convert").resolve(pathTxt);
        if (FileHelper.exists(fileTxt)) {
            final StringDictionary dictionary = new StringDictionary("=");
            try (InputStream stream = Files.newInputStream(fileTxt)) {
                dictionary.load(stream);
            } catch (Exception e) {
                SmartCNHelper.logger.warn("", e);
            }
            dictionary.walkEntries(primaryMap::put);
        }
        // load more
        loadMoreDictionaries(primaryMap);
        // build to trie
        long st = System.currentTimeMillis();
        trie.build(primaryMap);
        SmartCNHelper.logger.info("trie.build + " + (System.currentTimeMillis() - st));
        // save to bin
        TrieHelper.saveObject(trie, primaryMap, fileBin);
    }

    protected abstract void loadMoreDictionaries(TreeMap<String, String> primaryMap);

    protected static TreeMap<String, String> loadTxtDictionary(TreeMap<String, String> map, boolean reverse,
                                                               String... fileTxtNames) {
        StringDictionary dictionary = new StringDictionary("=");
        for (String fileTxtName : fileTxtNames) {
            try (InputStream stream = Files.newInputStream(SmartCNHelper.resolveData("convert/".concat(fileTxtName)))) {
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
