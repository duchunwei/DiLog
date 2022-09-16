package org.di.log;

public abstract class DiLogConfig {

    static int MAX_LEN = 512;
    static DiThreadFormatter DI_THREAD_FORMATTER = new DiThreadFormatter();
    static DiStackTraceFormatter DI_STACK_TRACE_FORMATTER = new DiStackTraceFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "DiLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public DiLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object object);
    }
}
