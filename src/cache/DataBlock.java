package cache;

public class DataBlock {
    private Object data;
    private Object key;
    public DataBlock next;
    public DataBlock prev;

    public DataBlock(Object data, Object key) {
        this.data = data;
        this.key = key;
        this.next = null;
        this.prev = null;
    }

    public Object getData() {
        return data;
    }

    public Object getKey() {
        return key;
    }

}
