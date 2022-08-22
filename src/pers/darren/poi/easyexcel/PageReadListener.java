package pers.darren.poi.easyexcel;

import cn.hutool.core.lang.Assert;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

public class PageReadListener<T> implements ReadListener<T> {

    private static final String errorMsgTemplate = "The argument {} is required; it must not be null.";
    private final Integer pageSize;
    private List<T> cachedDataList;
    private final Consumer<List<T>> consumer;

    public PageReadListener(@Nonnull Consumer<List<T>> consumer) {
        Assert.notNull(consumer, errorMsgTemplate, "[Consumer<List<T>> consumer]");
        this.pageSize = 100;
        this.consumer = consumer;
        this.cachedDataList = Lists.newArrayListWithExpectedSize(pageSize);
    }

    public PageReadListener(@Nonnull Integer pageSize, @Nonnull Consumer<List<T>> consumer) {
        Assert.notNull(pageSize, errorMsgTemplate, "[Integer pageSize]");
        Assert.notNull(consumer, errorMsgTemplate, "[Consumer<List<T>> consumer]");
        this.pageSize = pageSize;
        this.consumer = consumer;
        this.cachedDataList = Lists.newArrayListWithExpectedSize(pageSize);
    }

    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        this.cachedDataList.add(data);
        if (this.cachedDataList.size() >= this.pageSize) {
            this.consumer.accept(this.cachedDataList);
            this.cachedDataList = ListUtils.newArrayListWithExpectedSize(this.pageSize);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (CollectionUtils.isNotEmpty(this.cachedDataList)) {
            this.consumer.accept(this.cachedDataList);
        }
    }
}
