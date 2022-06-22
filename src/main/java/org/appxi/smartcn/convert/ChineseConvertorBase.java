package org.appxi.smartcn.convert;

import org.appxi.smartcn.util.SmartCNHelper;
import org.appxi.smartcn.util.dictionary.StringDictionary;
import org.appxi.smartcn.util.trie.DoubleArrayTrieByAhoCorasick;
import org.appxi.smartcn.util.trie.TrieHelper;
import org.appxi.util.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

abstract class ChineseConvertorBase extends ChineseConvertor {
    protected static final Logger logger = LoggerFactory.getLogger(ChineseConvertorBase.class);

    static {
        // 删除旧版数据
        FileHelper.deleteDirectory(SmartCNHelper.resolveData("convert"));
        FileHelper.deleteDirectory(SmartCNHelper.resolveCache("convert"));
    }

    protected final String id;

    protected ChineseConvertorBase(String id, String name) {
        super(name);
        this.id = id;
    }

    @Override
    protected final void loadDictionaries(DoubleArrayTrieByAhoCorasick<String> trie) {
        // default
        URLConnection txtFileDefault = null;
        try {
            txtFileDefault = getClass().getResource("data-" + id + ".txt").openConnection();
        } catch (Exception ignore) {
        }
        // user managed
        final Path txtFileManaged = SmartCNHelper.resolveData("convert." + id + ".txt");
        // cache file
        final Path binFile = SmartCNHelper.resolveCache("convert." + id + ".bin");
        // source files
        List<Object> txtSources = new ArrayList<>();
        if (null != txtFileDefault) {
            txtSources.add(txtFileDefault);
        }
        txtSources.add(txtFileManaged);
        txtSources.addAll(getDependencyTxtSources().stream()
                .map(url -> {
                    try {
                        return null == url ? null : url.openConnection();
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList());
        // 检查缓存bin文件是否需要重建
        if (!FileHelper.isTargetFileUpdatable(binFile, txtSources.toArray())) {
            // load from bin
            if (TrieHelper.loadObject(binFile, trie))
                return;
        }
        // load primary
        final TreeMap<String, String> primaryMap = new TreeMap<>();
        // 加载默认数据
        _load(primaryMap, txtFileDefault);
        // 加载管理的数据，可以覆盖默认数据
        _load(primaryMap, txtFileManaged);

        // load more
        loadDependencyTxtSources(primaryMap);
        // build to trie
        long st = System.currentTimeMillis();
        trie.build(primaryMap);
        logger.info("trie.build + " + (System.currentTimeMillis() - st));
        // save to bin
        TrieHelper.saveObject(trie, primaryMap, binFile);
    }

    private void _load(TreeMap<String, String> primaryMap, Object source) {
        if (null == source) {
            logger.warn("source is null");
            return;
        }

        final StringDictionary dictionary = new StringDictionary("=");
        //
        if (source instanceof Path path && FileHelper.exists(path)) {
            try (InputStream stream = Files.newInputStream(path)) {
                dictionary.load(stream);
            } catch (IOException e) {
                logger.warn("load Path failed", e);
            }
        } else if (source instanceof URLConnection urlConn) {
            try (InputStream stream = new BufferedInputStream(urlConn.getInputStream())) {
                dictionary.load(stream);
            } catch (IOException e) {
                logger.warn("load URL failed", e);
            }
        }
        //
        dictionary.walkEntries(primaryMap::put);
    }

    /**
     * 获得当前工具类所依赖的（内置）Txt源文件列表，用于检查是否有新的修改以更新缓存bin文件
     */
    protected abstract List<URL> getDependencyTxtSources();

    /**
     * 加载当前工具类所依赖的（内置）Txt源文件
     * <p>
     * 应该加载{@link #getDependencyTxtSources()}定义的Txt源文件列表
     */
    protected abstract void loadDependencyTxtSources(TreeMap<String, String> primaryMap);

    protected static TreeMap<String, String> loadTxtDictionary(TreeMap<String, String> map, boolean reverse, URL... txtFiles) {
        StringDictionary dictionary = new StringDictionary("=");
        for (URL txtFile : txtFiles) {
            if (null != txtFile) {
                try (InputStream stream = new BufferedInputStream(txtFile.openStream())) {
                    dictionary.load(stream);
                } catch (Exception e) {
                    logger.warn(txtFile.toString(), e);
                }
            }
        }
        if (reverse) {
            dictionary = dictionary.reverse();
        }
        dictionary.walkEntries(map::put);
        return map;
    }

    protected static TreeMap<String, String> loadTxtDictionary(boolean reverse, URL... txtFiles) {
        final TreeMap<String, String> result = new TreeMap<>();
        loadTxtDictionary(result, reverse, txtFiles);
        return result;
    }
}
