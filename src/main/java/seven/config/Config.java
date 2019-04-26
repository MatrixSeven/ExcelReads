package seven.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

public class Config {
    private static final Logger logger= LoggerFactory.getLogger(Config.class);

    private Integer titleRow = 1;
    private Integer contentRowStart = 2;
    private Integer contentRowEnd = null;
    private Boolean isLoopSheet = false;
    private Boolean errorLog = false;
    private Integer vocSize = 10000;
    @Deprecated
    private String[] require = null;
    private Integer startSheet = 0;
    private Integer endSheet = null;

    public Config() {
    }

    private boolean args(Object... params){
        return Arrays.stream(params).anyMatch(Objects::isNull);
    }

    public void check ()throws RuntimeException{
        if(args(titleRow,contentRowStart)){
            logger.error("config[titleRow,contentRowStart] is null, the config {}",this);
            throw new RuntimeException("onfig[titleRow,contentRowStart] is null");
        }else {
            logger.info("config[titleRow,contentRowStart] is null, the config {}",this);
        }

    }
    @Deprecated
    public String[] getRequire() {
        return require;
    }

    /**
     * Map数据个校验数组
     * <p>
     * 支持正则表达式
     *
     * @see seven.util.RegHelper
     */
    @Deprecated
    public void setRequire(String[] require) {
        this.require = require;
    }

    public Integer getVocSize() {
        return vocSize;
    }

    /**
     * Excel数据容器<br>
     * 当数据大于5w时，最好初始化为大于或者等于当前excel行数 最好设置大于或者
     *
     * @param vocSize
     */
    public Config vocSize(Integer vocSize) {
        this.vocSize = vocSize;
        return this;
    }

    public Boolean getErrorLog() {
        return errorLog;
    }

    public Config setErrorLog(Boolean errorLog) {
        this.errorLog = errorLog;
        return this;
    }


    /**
     * 内容开始行号
     */
    public Integer getTitleRow() {
        return titleRow;
    }

    /**
     * 标题行号
     */
    public Config title(Integer titleRow) {
        this.titleRow = titleRow;
        return this;
    }

    /**
     * 内容开始行号
     */
    public Integer getContentRowStart() {
        return contentRowStart;
    }

    /**
     * 内容开始行号
     *
     * @param contentRowStart
     */
    public Config content(Integer contentRowStart) {
        this.contentRowStart = contentRowStart;
        return this;
    }

    /**
     * 内容结束行号
     *
     * @return
     */
    public Integer getContentRowEnd() {
        return contentRowEnd;
    }

    /**
     * 内容结束行号
     */
    public Config contentRowEnd(Integer contentRowEnd) {
        this.contentRowEnd = contentRowEnd;
        return this;
    }

    public Boolean getIsLoopSheet() {
        return isLoopSheet;
    }

    public Config isLoopSheet(Boolean isLoopSheet) {
        this.isLoopSheet = isLoopSheet;
        return this;
    }

    public Integer getStartSheet() {
        return startSheet;
    }

    public Config startSheet(Integer startSheet) {
        this.startSheet = startSheet;
        return this;
    }

    public Integer getEndSheet() {
        return endSheet;
    }

    public Config endSheet(Integer endSheet) {
        this.endSheet = endSheet;
        return this;
    }

    @Override
    public String toString() {
        return "Config{" +
                "titleRow=" + titleRow +
                ", contentRowStart=" + contentRowStart +
                ", contentRowEnd=" + contentRowEnd +
                ", isLoopSheet=" + isLoopSheet +
                ", errorLog=" + errorLog +
                ", vocSize=" + vocSize +
                ", require=" + Arrays.toString(require) +
                ", startSheet=" + startSheet +
                ", endSheet=" + endSheet +
                '}';
    }


}