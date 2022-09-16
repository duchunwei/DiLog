package org.di.log;

import androidx.annotation.NonNull;


/**
 * 日志打印接口，基于接口可实现日志打印方式
 */
public interface DiLogPrinter {
    void print(@NonNull DiLogConfig logConfig, int level, String tag, @NonNull String printStr);
}
