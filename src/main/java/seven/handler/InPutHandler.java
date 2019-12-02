package seven.handler;

public interface InPutHandler<T> {
    default T Handler(String source) {
        if (source != null && !"".equals(source)) {
            return handlerConvert(source);
        }
        return null;
    }

    T handlerConvert(String source);

}
