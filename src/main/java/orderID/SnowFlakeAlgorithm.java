package orderID;

public class SnowFlakeAlgorithm {
    /**
     * Twitter的雪花算法SnowFlake
     * DateTime:2018-12-18 22:23:00
     * 1bit + 41bit + 5bit + 5bit + 12bit
     *
     * 时间戳(毫秒数)是根据当前时间获取的，datacenterId和workerId都是节点固定的值，
     * 因此，只需要确定sequence即可。
     * 这里最重要的就是序列号sequence的生成：需要判断是否为同一毫秒内；
     * （1）、若为同一毫秒，则sequence加一即可，若溢出(变为0)，需要等待下一毫秒的到来；
     * （2）、若不为同一毫秒，sequence置0即可。
     *
     * 代码实现原理：
     *  ID由四部分组成，确定四部分即可。
     *  首先定义各个部分占用的比特位数和组合时需要左移的位数；
     *  核心方法:nextId
     */
    private long twepoch = 1288834974657L;
    private long workerIdBits = 5L;//机器编号5位
    private long datacenterIdBits = 5L;//数据中心编号5位
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);//00...00011111;即31
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);//00...00011111;即31
    //需要左移的位数
    private long sequenceBits = 12L;//序列号12位
    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    //序列号
    private long sequenceMask = -1L ^ (-1L << sequenceBits);//0...0FFF；使用mask的目的是防止溢出
    //4部分：41bit + 5bit + 5bit + 12bit
    private long lastTimestamp = -1L;
    private long workerId;//机器编号
    private long datacenterId;//数据中心编号
    private long sequence;//序列号

    /**
     * 构造方法
     *
     * @param workerId
     * @param datacenterId
     * @param sequence
     */
    public SnowFlakeAlgorithm(long workerId, long datacenterId, long sequence) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d.",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
        System.out.println();//换行
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    /**
     * 核心方法：获取下一个Id
     *
     * @return Id
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {//同一个毫秒内，利用序列号区别
            sequence = (sequence + 1) & sequenceMask;//防止溢出
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {//不是一个毫秒数，则序列号置0
            sequence = 0;
        }
        lastTimestamp = timestamp;//更新上一个时间戳
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;//利用左移运算得到最终的ID
    }

    /**
     * 直到下一毫秒到来
     * 通过while循环实现
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {//确保下一毫秒的到来
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();//当前系统的毫秒值
    }

    //---------------测试---------------
    public static void main(String[] args) {
        SnowFlakeAlgorithm snowFlake = new SnowFlakeAlgorithm(1, 1, 1);
        for (int i = 0; i < 30; i++) {
            System.out.println(snowFlake.nextId());
        }
    }
}
