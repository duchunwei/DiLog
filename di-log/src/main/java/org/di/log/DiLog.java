package org.di.log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * DiLog
 * 1.打印堆栈信息
 * 2.File输出
 * 3.模拟控制台
 */
public class DiLog {

    private static final String DI_LOG_PACKAGE;

    static {
        String className = DiLog.class.getName();
        DI_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(DiLogType.V, contents);
    }

    public static void d(Object... contents) {
        log(DiLogType.D, contents);
    }

    public static void i(Object... contents) {
        log(DiLogType.I, contents);
    }

    public static void w(Object... contents) {
        log(DiLogType.W, contents);
    }

    public static void e(Object... contents) {
        log(DiLogType.E, contents);
    }

    public static void a(Object... contents) {
        log(DiLogType.A, contents);
    }

    public static void log(@DiLogType.TYPE int type, Object... contents) {
        log(type, DiLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@DiLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(DiLogManager.getInstance().getConfig(), type, tag, contents);
    }

    private static void log(@NonNull DiLogConfig config, @DiLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (config.includeThread()) {
            String threadInfo = DiLogConfig.DI_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (config.stackTraceDepth() > 0) {
            String stackTrace = DiLogConfig.DI_STACK_TRACE_FORMATTER.format(DiStackTraceUtil.getCroppedRealStackTrack(
                    new Throwable().getStackTrace(), DI_LOG_PACKAGE, config.stackTraceDepth()));
            sb.append(stackTrace).append("\n");
        }

        String body = parseBody(contents, config);
        if (body != null) {
            body = body.replace("\\\"", "\"");
        }
        sb.append("Content: ").append(body);
        List<DiLogPrinter> printers = config.printers() != null ? Arrays.asList(config.printers()) : DiLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        for (DiLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }

    private static String parseBody(@NonNull Object[] contents, @NonNull DiLogConfig config) {
        if (config.injectJsonParser() != null) {
            if (contents.length == 1 && contents[0] instanceof String) {
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
