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
import seven.callBack.imp.DefaultDataFilter;
import seven.callBack.imp.DefaultDataProFilter;
import seven.wapperInt.ReaderMap;
import seven.wapperInt.Wrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Seven<p>
 * date   2016年4月12日-下午4:07:57
 */
@SuppressWarnings("all")
public abstract class WrapperMap<T> extends Wrapper implements ReaderMap {
    private static final Logger logger = LoggerFactory.getLogger(WrapperMap.class);

    public WrapperMap(Consumer<seven.config.Config> consumer) {
        super(consumer);
    }


    protected boolean isNull(Map<String, String> map) {
        boolean isN = true;
        for (Map.Entry<String, String> s : map.entrySet()) {
            isN = isN && s.getValue().trim().equals("");
        }
        return isN;
    }


    public Wrapper init(String filePath) {
        this.fs = filePath;
        return this;
    }

    public Wrapper init(File file) {
        this.fs = file.getPath();
        this.file = file;
        return this;
    }

    public ReaderMap Sort(Comparator c) {
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
    public ReaderMap FilterCol(Consumer<List<String>> consumer) {
        consumer.accept(filterColByKey);
        return this;
    }

    @Override
    public List<Map<String, String>> CreateMap() throws Exception {
        if (config.getIsLoopSheet()) {
            throw new RuntimeException("开启LoopSheet选项,请使用CreateMapLoop");
        }
        return refResWrapper(fs, isMap);
    }

    @Override
    public Map<String, Map<String, String>> CreateMapLoop() throws Exception {
        if (!config.getIsLoopSheet()) {
            throw new RuntimeException("未开启LoopSheet选项,请使用CreateMap");
        }
        return refResWrapper(fs, isMap);
    }


    @Override
    public ReaderMap Filter(DataFilterInterface<? extends Map> filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public ReaderMap Process(DataFilterProcessInterface<? extends Map> process) {
        this.process = process;
        return this;
    }


}
