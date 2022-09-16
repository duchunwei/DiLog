package org.di.log;

import android.util.Log;

import androidx.annotation.NonNull;

import static org.di.log.DiLogConfig.MAX_LEN;

public class DiConsolePrinter implements DiLogPrinter {
    @Override
    public void print(@NonNull DiLogConfig logConfig, int level, String tag, @NonNull String printStr) {
        int len = printStr.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(printStr.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }

            if (index != len) {
                log.append(printStr.substring(index, len));
            }
            Log.println(level, tag, log.toString());
        } else {
            Log.println(level, tag, printStr);
        }
    }
}
