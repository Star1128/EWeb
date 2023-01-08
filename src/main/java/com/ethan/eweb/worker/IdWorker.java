package com.ethan.eweb.worker;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 雪花算法 随机生成ID
 *
 * @author Ethan 2023/1/7
 */
public class IdWorker {

    // 以项目开始时间计时，可以使用更久
    private static final long twepoch = 1673065505000L;
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = 31L;
    private static final long maxDatacenterId = 31L;
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = 12L;
    private static final long datacenterIdShift = 17L;
    private static final long timestampLeftShift = 22L;
    private static final long sequenceMask = ~(-1L << sequenceBits);
    private static long lastTimestamp = -1L;
    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;

    public IdWorker() {
        this.datacenterId = getDatacenterId();
        this.workerId = getWorkerId(datacenterId);
    }

    public IdWorker(long workerId, long datacenterId) {
        if (workerId <= maxWorkerId && workerId >= 0L) {
            if (datacenterId <= maxDatacenterId
                    && datacenterId >= 0L) {
                this.workerId = workerId;
                this.datacenterId = datacenterId;
            } else {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
            }
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
    }

    /**
     * 结合 JVM 名称生成机器 ID
     */
    private static long getWorkerId(long datacenterId) {
        StringBuilder mpid = new StringBuilder();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName(); // 拿到 JVM 名称
        System.out.println(name);
        if (!name.isEmpty()) {
            mpid.append(name.split("@")[0]);
        }

        return (long) (mpid.toString().hashCode() & '\uffff') % (maxWorkerId + 1L);
    }

    /**
     * 结合主机 IP 地址和 MAC 地址生成数据中心 ID
     */
    private static long getDatacenterId() {
        long id = 0L;

        try {
            InetAddress ip = InetAddress.getByName("192.168.1.103");
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = (255L & (long) mac[mac.length - 1] | 65280L & (long) mac[mac.length - 2] << 8) >> 6;
                id %= maxDatacenterId + 1L;
            }
        } catch (Exception exn) {
            System.out.println(" getDatacenterId: " + exn.getMessage());
        }

        return id;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        } else {
            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1L) & sequenceMask;
                if (sequence == 0L) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;
            // 四者结合运算得出 ID
            return timestamp - twepoch << timestampLeftShift | this.datacenterId << datacenterIdShift | this.workerId << workerIdShift | this.sequence;
        }
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 发生时钟回拨时校准时间
     */
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp;
        // 空转大法
        for (timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }
}
