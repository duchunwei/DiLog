# DiLog
DiLog is a log printer.

# 调用方式
1. 初始化
 DiLogManager.init(new DiLogConfig() {
            @Override
            public JsonParser injectJsonParser() {
                return object -> new Gson().toJson(object);
            }

            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 10;
            }

            @Override
            public String getGlobalTag() {
                return "dcw";
            }
        }, new DiConsolePrinter(), new DiFilePrinter(getCacheDir().getAbsolutePath(), 0));

    }

2.调用
  DiLog.d("日志")

3.视图打印
  val diViewPrinter = DiViewPrinter(this)
  diViewPrinter.viewProvider.showFloatingView()
  DiLogManager.getInstance().addPrinter(diViewPrinter)
        
 DiLog.d("日志")
