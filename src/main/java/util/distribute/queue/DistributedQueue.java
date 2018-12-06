package util.distribute.queue;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import util.string.StringUtil;

import java.lang.reflect.ParameterizedType;

/**
 * 分布式队列, 需要配合分布式锁使用。本身内部并未做任何同步处理
 */
public class DistributedQueue<T> {

    private final String   name;       //队列名字
    private final Long     capacity;   //队列大小
    private final String   queueKey;   //队列redis的key
    private final Jedis jedis;   //redis

    public DistributedQueue(String name, String queueKey, Jedis jedis){
        this(name, queueKey, jedis, Long.MAX_VALUE);
    }

    public DistributedQueue(String name, String queueKey, Jedis jedis, Long capacity){
        this.name = name;
        this.capacity = capacity;
        this.queueKey = queueKey;
        this.jedis = jedis;
    }

    public boolean offerLast(T t){
        return offer(t, Direction.TAIL);
    }

    public boolean offerFirst(T t){
        return offer(t, Direction.HEAD);
    }

    private boolean offer(T t, Direction direction){
        String text;long num;
        boolean isExist = jedis.exists(queueKey);
        if(!isExist){
            text = JSON.toJSONString(t);
            num = jedis.lpush(text);
            return num > 0 ? true : false;
        }

        long size = jedis.llen(queueKey);
        if(size >= capacity) return false;
        text = JSON.toJSONString(t);
        if(direction == Direction.HEAD){
            num = jedis.lpush(text);
        }else {
            num = jedis.rpush(text);
        }
        return num > 0 ? true : false;
    }

    public T poll(){
        String text;
        boolean isExist = jedis.exists(queueKey);
        if(!isExist) return null;
        text = jedis.lpop(queueKey);
        if(StringUtil.isEmpty(text)) return null;
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return JSON.parseObject(text,tClass);
    }

    public void clear(){
        jedis.del(queueKey);
    }

    public long size(){
        boolean isExist = jedis.exists(queueKey);
        if(!isExist) return 0;
        return jedis.llen(queueKey);
    }

    @Override
    public String toString() {
        return "DistributedQueue{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", queueKey='" + queueKey + '\'' +
                '}';
    }
}
