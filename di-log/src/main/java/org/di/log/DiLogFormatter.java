package org.di.log;

public interface DiLogFormatter<T> {
    String format(T data);
}
