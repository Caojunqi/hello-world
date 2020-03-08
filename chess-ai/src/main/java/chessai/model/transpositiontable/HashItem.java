package chessai.model.transpositiontable;

/**
 * 置换表中插入的数据项
 */
public class HashItem {

    /**
     * 数据类型
     */
    private HashItemType itemType;
    /**
     * 当前棋局的64位校验码
     */
    private long checkSum;
    /**
     * 取得此值时的层次
     */
    private int depth;
    /**
     * 节点的估值
     */
    private int value;

    private HashItem() {
        // 私有化构造器
    }

    public static HashItem valueOf(HashItemType itemType, long checkSum, int depth, int value) {
        HashItem item = new HashItem();
        item.itemType = itemType;
        item.checkSum = checkSum;
        item.depth = depth;
        item.value = value;
        return item;
    }

    public HashItemType getItemType() {
        return itemType;
    }

    public long getCheckSum() {
        return checkSum;
    }

    public int getDepth() {
        return depth;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "HashItem{" +
                "itemType=" + itemType +
                ", checkSum=" + checkSum +
                ", depth=" + depth +
                ", value=" + value +
                '}';
    }
}
