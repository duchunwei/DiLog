package org.di.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DiLog管理类
 */
public class DiLogManager {

    private DiLogConfig config;
    private static DiLogManager instance;
    private List<DiLogPrinter> printers = new ArrayList<>();

    public DiLogManager(DiLogConfig config, DiLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static DiLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull DiLogConfig config, DiLogPrinter... printers) {
        instance = new DiLogManager(config, printers);
    }

    public DiLogConfig getConfig() {
        return config;
    }

    public List<DiLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(DiLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(DiLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }
}
