package org.di.log;

public class DiThreadFormatter implements DiLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
