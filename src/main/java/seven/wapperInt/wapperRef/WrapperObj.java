package seven.wapperInt.wapperRef;


//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/#\
//		 .' @          /###\
//		 :         ,   #####
//		  `-..__.-' _.-\###/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //狗神保佑  \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;
import seven.wapperInt.ReaderObj;
import seven.wapperInt.Wrapper;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author Seven<p>
 * date   2016年4月12日-下午4:07:57
 */
@SuppressWarnings("all")
public abstract class WrapperObj<T> extends Wrapper implements ReaderObj<T> {
    private static final Logger logger = LoggerFactory.getLogger(WrapperObj.class);





    public WrapperObj(Consumer<seven.config.Config> consumer) {
        super(consumer);
    }


    protected boolean isNull(Map<String, String> map) {
        boolean isN = true;
        for (Map.Entry<String, String> s : map.entrySet()) {
            isN = isN && s.getValue().trim().equals("");
        }
        return isN;
    }


    public ReaderObj<T> init(String filePath) {
        this.fs = filePath;
        return this;
    }

    public ReaderObj<T> init(File file) {
        this.fs = file.getPath();
        this.file=file;
        return this;
    }

    public ReaderObj<T> Sort(Comparator c) {
        this.c = c;
        return this;
    }

    public List<T> Create() throws Exception {
        if (config.getIsLoopSheet()) {
            throw new RuntimeException("未开启LoopSheet选项,请使用CreateObjLoop");
        }
        return refResWrapper(fs, isMap);
    }

    @Override
    public ReaderObj<T> FilterCol(Consumer<List<String>> consumer) {
        consumer.accept(filterColByKey);
        return this;
    }


    @Override
    public Map<String, List<T>> CreateObjLoop() throws Exception {
        if (!config.getIsLoopSheet()) {
            throw new RuntimeException("未开启LoopSheet选项,请使用Create");
        }
        return refResWrapper(fs, isMap);
    }


    @Override
    public ReaderObj<T> Filter(DataFilterInterface<? extends T> filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public ReaderObj<T> Process(DataFilterProcessInterface<? extends T> process) {
        this.process = process;
        return this;
    }


}
