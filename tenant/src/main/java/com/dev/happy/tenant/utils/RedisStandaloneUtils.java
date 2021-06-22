package com.dev.happy.tenant.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisStandaloneUtils {
    private final static String OK = "OK";
    private final static String NIL = "nil";
    private Jedis jedis;

    public RedisStandaloneUtils(String host,Integer port) {
        jedis = new Jedis(host, port);
    }
    //##########################################  String  Start ###################################################

    /**
     * 检查给定 key 是否存在
     * 若 key 存在返回 true ，否则返回 false 。
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return jedis.exists(key);
    }

    /**
     * 字符串-设置键值，永不过期
     *
     * @param key   key
     * @param value value
     * @return 设置结果，成功返回true
     */
    public boolean set(String key, String value) {
        String res = jedis.set(key, value);
        return OK.equals(res);
    }

    /**
     * 删除key
     *
     * @param key key
     * @return 设置结果，成功返回true
     */
    public boolean del(String key) {
        long res = jedis.del(key);
        return res > 0;
    }

    /**
     * 字符串-设置键值，永不过期.设置前判断键是否存在，只有不存在才会设置成功
     *
     * @param key   key
     * @param value value
     * @return 设置结果，成功返回true
     */
    public boolean setnx(String key, String value) {
        long res = jedis.setnx(key, value);
        return res == 1;
    }

    /**
     * 字符串-设置键值，自定义过期秒数.设置前判断键是否存在，只有不存在才会设置成功
     *
     * @param key     key
     * @param value   value
     * @param seconds 过期秒数
     * @return 设置结果，成功返回true
     */
    public boolean setnx(String key, String value, long seconds) {
        String res = jedis.set(key, value, SetParams.setParams().nx().ex(seconds));
        return OK.equals(res);
    }

    /**
     * 字符串-设置键值，自定义过期秒数
     *
     * @param key     key
     * @param value   value
     * @param seconds 过期秒数
     * @return 设置结果，成功返回true
     */
    public boolean setex(String key, String value, long seconds) {
        String res = jedis.setex(key, seconds, value);
        return OK.equals(res);
    }

    /**
     * 字符串-设置键值，自定义过期时间（支持毫秒，秒、分钟、小时、天）
     *
     * @param key         key
     * @param value       value
     * @param expiredTime 过期时间
     * @param timeUnit    时间单位
     * @return 设置结果，成功返回true
     */
    public boolean setex(String key, String value, int expiredTime, TimeUnit timeUnit) {
        int time;
        switch (timeUnit) {
            case MILLISECONDS:
                time = expiredTime;
                break;
            case SECONDS:
                time = expiredTime * 1000;
                break;
            case MINUTES:
                time = expiredTime * 1000 * 60;
                break;
            case HOURS:
                time = expiredTime * 1000 * 60 * 60;
                break;
            case DAYS:
                time = expiredTime * 1000 * 60 * 60 * 24;
                break;
            default:
                time = -1;
        }
        String res = jedis.psetex(key, time, value);
        return OK.equals(res);
    }

    /**
     * 字符串-设置键值，自定义过期毫秒数
     *
     * @param key         key
     * @param value       value
     * @param millisecond 过期毫秒数
     */
    public boolean psetex(String key, String value, long millisecond) {
        String res = jedis.psetex(key, millisecond, value);
        return OK.equals(res);
    }

    /**
     * 字符串-获取键对应的值，不存在的键返回null
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        String res = jedis.get(key);
        if (NIL.equals(res)) {
            return null;
        }
        return res;
    }

    /**
     * 将键 key 的值设为 value ， 并返回键 key 在被设置之前的旧值。
     *
     * @param key   key
     * @param value value
     * @return 返回给定键 key 的旧值。
     */
    public String getSet(String key, String value) {
        String res = jedis.getSet(key, value);
        if (NIL.equals(res)) {
            return null;
        }
        return res;
    }

    /**
     * 返回键 key 储存的字符串值的长度。
     *
     * @param key key
     * @return 返回字符串值的长度。当键 key 不存在时， 命令返回 0 。
     */
    public Long strlen(String key) {
        return jedis.strlen(key);
    }

    /**
     * 拼接字符串<br>
     * 如果键 key 已经存在并且它的值是一个字符串， APPEND 命令将把 value 追加到键 key 现有值的末尾。<br>
     * 如果 key 不存在， APPEND 就简单地将键 key 的值设为 value ， 就像执行 SET key value 一样。<br>
     *
     * @param key   key
     * @param value value
     * @return 追加 value 之后， 键 key 的值的长度。
     */
    public Long append(String key, String value) {
        return jedis.append(key, value);
    }

    /**
     * 为键 key 储存的数字值加上一。<br>
     * 如果键 key 不存在， 那么它的值会先被初始化为 0 ， 然后再执行 INCR 命令。<br>
     * 如果键 key 储存的值不能被解释为数字， 那么 INCR 命令将返回一个错误。<br>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br>
     *
     * @param key key
     * @return 键 key 在执行加一操作之后的值。
     */
    public Long incr(String key) {
        return jedis.incr(key);
    }

    /**
     * 为键 key 储存的数字值加上增量 increment。<br>
     * 如果键 key 不存在， 那么键 key 的值会先被初始化为 0 ， 然后再执行 INCRBY 命令。<br>
     * 如果键 key 储存的值不能被解释为数字， 那么 INCRBY 命令将返回一个错误。<br>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br>
     *
     * @param key       key
     * @param increment increment
     * @return 在加上增量 increment 之后， 键 key 当前的值。
     */
    public Long incrBy(String key, long increment) {
        return jedis.incrBy(key, increment);
    }

    /**
     * 为键 key 储存的值加上浮点数增量 increment。<br>
     * 如果键 key 不存在， 那么 INCRBYFLOAT 会先将键 key 的值设为 0 ， 然后再执行加法操作。<br>
     * 如果命令执行成功， 那么键 key 的值会被更新为执行加法计算之后的新值， 并且新值会以字符串的形式返回给调用者。<br>
     *
     * @param key       key
     * @param increment increment
     * @return 在加上增量 increment 之后， 键 key 的值。
     */
    public double incrByFloat(String key, double increment) {
        return jedis.incrByFloat(key, increment);
    }

    /**
     * 为键 key 储存的数字值减去一。<br>
     * 如果键 key 不存在， 那么键 key 的值会先被初始化为 0 ， 然后再执行 DECR 操作。<br>
     * 如果键 key 储存的值不能被解释为数字， 那么 DECR 命令将返回一个错误。<br>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br>
     *
     * @param key key
     * @return 键 key 在执行减一操作之后的值。
     */
    public Long decr(String key) {
        return jedis.decr(key);
    }

    /**
     * 将键 key 储存的整数值减去减量 decrement 。<br>
     * 如果键 key 不存在， 那么键 key 的值会先被初始化为 0 ， 然后再执行 DECRBY 命令。<br>
     * 如果键 key 储存的值不能被解释为数字， 那么 DECRBY 命令将返回一个错误。<br>
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。<br>
     *
     * @param key       key
     * @param decrement decrement
     * @return 键在执行减法操作之后的值。
     */
    public Long decrBy(String key, long decrement) {
        return jedis.decrBy(key, decrement);
    }

    /**
     * 同时为多个键设置值。<br>
     * 如果某个给定键已经存在， 那么 MSET 将使用新值去覆盖旧值， 如果这不是你所希望的效果， 请考虑使用 MSETNX 命令， 这个命令只会在所有给定键都不存在的情况下进行设置。<br>
     *
     * @param keyValue keyValue
     */
    public void mset(String... keyValue) {
        jedis.mset(keyValue);
    }

    /**
     * 当且仅当所有给定键都不存在时， 为所有给定键设置值。<br>
     * 即使只有一个给定键已经存在， MSETNX 命令也会拒绝执行对所有键的设置操作。<br>
     *
     * @param keyValue keyValue
     */
    public void msetnx(String... keyValue) {
        jedis.msetnx(keyValue);
    }

    /**
     * 返回给定的一个或多个字符串键的值。<br>
     * 如果给定的字符串键里面， 有某个键不存在， 那么这个键的值将以null表示。<br>
     *
     * @param keys keys
     * @return 返回一个列表， 列表中包含了所有给定键的值。
     */
    public List<String> mget(String... keys) {
        List<String> res = jedis.mget(keys);
        List<String> outList = new ArrayList<>(res.size());
        res.forEach(x -> {
            String temp = x;
            if (NIL.equals(x)) {
                temp = null;
            }
            outList.add(temp);
        });
        return outList;
    }

    //##########################################  String  End ###################################################

    //##########################################  Hash  Start ###################################################

    /**
     * 将哈希表 hash 中域 field 的值设置为 value 。<br>
     * 如果给定的哈希表并不存在， 那么一个新的哈希表将被创建并执行 HSET 操作。<br>
     * 如果域 field 已经存在于哈希表中， 那么它的旧值将被新值 value 覆盖。<br>
     *
     * @param key   key
     * @param field field
     * @param value value
     * @return 在哈希表中新创建 field 域并成功为它设置值时，返回 1； 如果域 field 已经存在于哈希表并且 HSET命令成功使用新值覆盖了它的旧值，那么命令返回 0 。
     */
    public Long hset(String key, String field, String value) {
        return jedis.hset(key, field, value);
    }

    /**
     * 批量更新哈希表 hash
     *
     * @param key         key
     * @param fieldValues fieldValues
     * @return 成功的数量
     */
    public Long hset(String key, Map<String, String> fieldValues) {
        return jedis.hset(key, fieldValues);
    }

    /**
     * 当且仅当域 field 尚未存在于哈希表的情况下， 将它的值设置为 value 。<br>
     * 如果给定域已经存在于哈希表当中， 那么命令将放弃执行设置操作。<br>
     * 如果哈希表 hash 不存在， 那么一个新的哈希表将被创建并执行 HSETNX 命令。<br>
     *
     * @param key   key
     * @param field field
     * @param value value
     * @return
     */
    public boolean hsetnx(String key, String field, String value) {
        long res = jedis.hsetnx(key, field, value);
        return res == 1;
    }

    /**
     * 哈希表中给定域的值
     *
     * @param key   key
     * @param field field
     * @return 给定域的值。如果给定域不存在于哈希表中， 又或者给定的哈希表并不存在则返回 null 。
     */
    public String hget(String key, String field) {
        String res = jedis.hget(key, field);
        if (NIL.equals(res)) {
            return null;
        }
        return res;
    }

    /**
     * 检查给定域 field 是否存在于哈希表 hash 当中
     *
     * @param key   key
     * @param field field
     * @return result
     */
    public boolean hexists(String key, String field) {
        return jedis.hexists(key, field);
    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略
     *
     * @param key    key
     * @param fields fields
     * @return 被成功移除的域的数量，不包括被忽略的域。
     */
    public Long hdel(String key, String... fields) {
        return jedis.hdel(key, fields);
    }

    /**
     * 返回哈希表 key 中域的数量。
     *
     * @param key key
     * @return 哈希表中域的数量。当 key 不存在时，返回 0 。
     */
    public Long hlen(String key) {
        return jedis.hlen(key);
    }

    /**
     * 返回哈希表 key 中，与给定域 field 相关联的值的字符串长度。
     *
     * @param key   key
     * @param field field
     * @return 值的字符串长度
     */
    public Long hstrlen(String key, String field) {
        return jedis.hstrlen(key, field);
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment。<br>
     * 增量也可以为负数，相当于对给定域进行减法操作。<br>
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。<br>
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。<br>
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。<br>
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。<br>
     *
     * @param key       key
     * @param field     field
     * @param increment increment
     * @return 执行 HINCRBY 命令之后，哈希表 key 中域 field 的值。
     */
    public Long hincrBy(String key, String field, long increment) {
        return jedis.hincrBy(key, field, increment);
    }

    /**
     * 为哈希表 key 中的域 field 加上浮点数增量 increment。<br>
     * 如果哈希表中没有域 field ，那么 HINCRBYFLOAT 会先将域 field 的值设为 0 ，然后再执行加法操作。<br>
     * 如果键 key 不存在，那么 HINCRBYFLOAT 会先创建一个哈希表，再创建域 field ，最后再执行加法操作。<br>
     *
     * @param key       key
     * @param field     field
     * @param increment increment
     * @return 执行加法操作之后 field 域的值。
     */
    public Double hincrByFloat(String key, String field, double increment) {
        return jedis.hincrByFloat(key, field, increment);
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key中。<br>
     * 此命令会覆盖哈希表中已存在的域。<br>
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET操作。<br>
     *
     * @param key         key
     * @param fieldValues fieldValues
     * @return 如果命令执行成功，返回true 。
     */
    public boolean hmset(String key, Map<String, String> fieldValues) {
        String res = jedis.hmset(key, fieldValues);
        return OK.equals(res);
    }

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。<br>
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。<br>
     * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个null的表。<br>
     *
     * @param key    key
     * @param fields fields
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
     */
    public List<String> hmget(String key, String... fields) {
        List<String> res = jedis.hmget(key, fields);
        List<String> out = new ArrayList<>(res.size());
        res.forEach(x -> {
            if (NIL.equals(x)) {
                out.add(null);
            } else {
                out.add(x);
            }
        });
        return out;
    }

    /**
     * 哈希表 key 中的所有域
     *
     * @param key key
     * @return 一个包含哈希表中所有域的表。当 key 不存在时，返回一个空表。
     */
    public Set<String> hkeys(String key) {
        return jedis.hkeys(key);
    }

    /**
     * 哈希表 key 中所有域的值
     *
     * @param key key
     * @return 一个包含哈希表中所有值的表。当 key 不存在时，返回一个空表。
     */
    public List<String> hvals(String key) {
        return jedis.hvals(key);
    }

    /**
     * 哈希表 key 中，所有的域和值
     *
     * @param key key
     * @return field-value的Map
     */
    public Map<String, String> hgetAll(String key) {
        return jedis.hgetAll(key);
    }

    //##########################################  Hash  End ###################################################

    //##########################################  List  Start ###################################################

    /**
     * 将一个或多个值 value 插入到列表 key 的表头<br>
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头：<br>
     * eg，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。<br>
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。<br>
     *
     * @param key    key
     * @param values values
     * @return LPUSH 命令执行之后，表的长度。
     */
    public Long lpush(String key, String... values) {
        return jedis.lpush(key, values);
    }


    /**
     * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。<br>
     * 和 LPUSH命令相反，当 key 不存在时， LPUSHX 命令什么也不做。<br>
     *
     * @param key    key
     * @param values values
     * @return LPUSHX 命令执行之后，表的长度。
     */
    public Long lpushx(String key, String... values) {
        return jedis.lpushx(key, values);
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。<br>
     * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：<br>
     * eg:对一个空列表 mylist 执行 RPUSH mylist a b c ，得出的结果列表为 a b c ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。<br>
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。<br>
     *
     * @param key    key
     * @param values values
     * @return 执行 RPUSH 操作后，表的长度。
     */
    public Long rpush(String key, String... values) {
        return jedis.rpush(key, values);
    }

    /**
     * 移除并返回列表 key 的头元素。
     *
     * @param key key
     * @return 列表的头元素。 当 key 不存在时，返回 null 。
     */
    public String lpop(String key) {
        String res = jedis.lpop(key);
        if (NIL.equals(res)) {
            return null;
        }
        return res;
    }

    /**
     * 移除并返回列表 key 的尾元素。
     *
     * @param key key
     * @return 列表的尾元素。 当 key 不存在时，返回 null 。
     */
    public String rpop(String key) {
        String res = jedis.rpop(key);
        if (NIL.equals(res)) {
            return null;
        }
        return res;
    }

    /**
     * 命令 RPOPLPUSH 在一个原子时间内，执行以下两个动作：<br>
     * 将列表 srcKey 中的最后一个元素(尾元素)弹出，并返回给客户端。<br>
     * 将 srcKey 弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素。<br>
     *
     * @param srcKey  srcKey
     * @param destKey destKey
     */
    public void rpoppush(String srcKey, String destKey) {
        jedis.rpoplpush(srcKey, destKey);
    }

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。<br>
     * count 的值可以是以下几种：<br>
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。<br>
     * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。<br>
     * count = 0 : 移除表中所有与 value 相等的值。<br>
     *
     * @param key
     * @param count
     * @param value
     * @return 被移除元素的数量。 因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
     */
    public Long lrem(String key, long count, String value) {
        return jedis.lrem(key, count, value);
    }

    /**
     * 列表 key 的长度
     *
     * @param key key
     * @return 列表 key 的长度
     */
    public Long llen(String key) {
        return jedis.llen(key);
    }

    /**
     * 返回列表 key 中，下标为 index 的元素。<br>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br>
     *
     * @param key   key
     * @param index index
     * @return 列表中下标为 index 的元素。 如果 index 参数的值不在列表的区间范围内(out of range)，返回 null 。
     */
    public String lindex(String key, long index) {
        String res = jedis.lindex(key, index);
        if (NIL.equals(res)) {
            return null;
        }
        return res;
    }

    /**
     * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。<br>
     * 当 pivot 不存在于列表 key 时，不执行任何操作。<br>
     * 当 key 不存在时， key 被视为空列表，不执行任何操作<br>
     *
     * @param key   key
     * @param where BEFORE|AFTER
     * @param pivot pivot
     * @param value value
     * @return 如果命令执行成功，返回插入操作完成之后，列表的长度。 如果没有找到 pivot ，返回 -1 。 如果 key 不存在或为空列表，返回 0
     */
    public long linsert(String key, ListPosition where, String pivot, String value) {
        return jedis.linsert(key, where, pivot, value);
    }

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value 。
     *
     * @param key   key
     * @param index index
     * @param value value
     * @return result
     */
    public boolean lset(String key, long index, String value) {
        String res = jedis.lset(key, index, value);
        return OK.equals(res);
    }

    /**
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。<br>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br>
     * 超出范围的下标：<br>
     * 超出范围的下标值不会引起错误。<br>
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，那么 LRANGE 返回一个空列表。<br>
     * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。<br>
     *
     * @param key   key
     * @param start start 从0开始
     * @param stop  stop
     * @return 一个列表，包含指定区间内的元素
     */
    public List<String> lrange(String key, long start, long stop) {
        return jedis.lrange(key, start, stop);
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。<br>
     * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。<br>
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br>
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br>
     * 超出范围的下标:<br>
     * 超出范围的下标值不会引起错误。<br>
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start > stop ， LTRIM 返回一个空列表(因为 LTRIM 已经将整个列表清空)。<br>
     * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。<br>
     *
     * @param key   key
     * @param start start 从0开始
     * @param stop  stop
     * @return result
     */
    public boolean ltrim(String key, long start, long stop) {
        String res = jedis.ltrim(key, start, stop);
        return OK.equals(res);
    }

    /**
     * LPOP key 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止。<br>
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素。<br>
     * 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely) 。
     *
     * @param timeout 超时秒数
     * @param keys    keys
     * @return 如果列表为空，返回一个 nil 。 否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     */
    public List<String> blpop(int timeout, String... keys) {
        return jedis.blpop(timeout, keys);
    }

    /**
     * BRPOP 是列表的阻塞式(blocking)弹出原语。<br>
     * 它是 RPOP key 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止。<br>
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的尾部元素。<br>
     *
     * @param timeout 超时秒数
     * @param keys    keys
     * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。 反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     */
    public List<String> brpop(int timeout, String... keys) {
        return jedis.brpop(timeout, keys);
    }

    /**
     * BRPOPLPUSH 是 RPOPLPUSH source destination 的阻塞版本，当给定列表 source 不为空时， BRPOPLPUSH 的表现和 RPOPLPUSH source destination 一样。<br>
     * 当列表 source 为空时， BRPOPLPUSH 命令将阻塞连接，直到等待超时，或有另一个客户端对 source 执行 LPUSH key value [value …] 或 RPUSH key value [value …] 命令为止。<br>
     * 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely) 。<br>
     *
     * @param source      source
     * @param destination destination
     * @param timeout     超时秒数
     * @return
     */
    public String brpoplpush(String source, String destination, int timeout) {
        return jedis.brpoplpush(source, destination, timeout);
    }
    //##########################################  Hash  End ###################################################

    //##########################################  Set  Start ###################################################

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。<br>
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。<br>
     *
     * @param key     key
     * @param members members
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
     */
    public Long sadd(String key, String... members) {
        return jedis.sadd(key, members);
    }

    /**
     * 判断 member 元素是否集合 key 的成员
     *
     * @param key    key
     * @param member member
     * @return result
     */
    public boolean sismember(String key, String member) {
        return jedis.sismember(key, member);
    }

    /**
     * 移除并返回集合中的一个随机元素
     *
     * @param key key
     * @return 被移除的随机元素。 当 key 不存在或 key 是空集时，返回 null 。
     */
    public String spop(String key) {
        String res = jedis.spop(key);
        if (NIL.equals(res)) {
            return null;
        }
        return res;
    }

    /**
     * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。<br>
     * 从 Redis 2.6 版本开始， SRANDMEMBER 命令接受可选的 count 参数：<br>
     * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。<br>
     * 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。<br>
     *
     * @param key   key
     * @param count count
     * @return 只提供 key 参数时，返回一个元素；如果集合为空，返回 null 。 如果提供了 count 参数，那么返回一个数组；如果集合为空，返回空数组
     */
    public List<String> srandmember(String key, int count) {
        List<String> res = jedis.srandmember(key, count);
        if (res.contains(NIL)) {
            return null;
        }
        return res;
    }

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     *
     * @param key     key
     * @param members members
     * @return 被成功移除的元素的数量，不包括被忽略的元素。
     */
    public long srem(String key, String... members) {
        return jedis.srem(key, members);
    }

    /**
     * 将 member 元素从 source 集合移动到 destination 集合。
     * SMOVE 是原子性操作。
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 false 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。
     * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
     *
     * @param source      source
     * @param destination destination
     * @param member      member
     * @return result
     */
    public boolean smove(String source, String destination, String member) {
        long res = jedis.smove(source, destination, member);
        return res == 1;
    }

    /**
     * 返回集合 key 的基数(集合中元素的数量)
     *
     * @param key
     * @return 集合的基数。 当 key 不存在时，返回 0 。
     */
    public Long scard(String key) {
        return jedis.scard(key);
    }

    /**
     * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
     *
     * @param key key
     * @return 集合中的所有成员。
     */
    public Set<String> smembers(String key) {
        return jedis.smembers(key);
    }

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合的交集。<br>
     * 不存在的 key 被视为空集。<br>
     * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。<br>
     *
     * @param keys keys
     * @return 交集成员的列表。
     */
    public Set<String> sinter(String... keys) {
        return jedis.sinter(keys);
    }

    /**
     * 这个命令类似于 SINTER key [key …] 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
     * 如果 destination 集合已经存在，则将其覆盖。 destination 可以是 key 本身。
     *
     * @param destination destination
     * @param keys        keys
     * @return 结果集中的成员数量。
     */
    public Long sinterstore(String destination, String... keys) {
        return jedis.sinterstore(destination, keys);
    }

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合的并集。 不存在的 key 被视为空集。
     *
     * @param keys keys
     * @return 并集成员的列表。
     */
    public Set<String> sunion(String... keys) {
        return jedis.sunion(keys);
    }

    /**
     * 这个命令类似于 SUNION key [key …] 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。<br>
     * 如果 destination 已经存在，则将其覆盖。destination 可以是 key 本身。
     *
     * @param destination destination
     * @param keys        keys
     * @return 结果集中的元素数量。
     */
    public Long sunionstore(String destination, String... keys) {
        return jedis.sunionstore(destination, keys);
    }

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合之间的差集。不存在的 key 被视为空集。
     *
     * @param keys
     * @return 一个包含差集成员的列表。
     */
    public Set<String> sdiff(String... keys) {
        return jedis.sdiff(keys);
    }

    /**
     * 这个命令的作用和 SDIFF key [key …] 类似，但它将结果保存到 destination 集合，而不是简单地返回结果集。<br>
     * 如果 destination 集合已经存在，则将其覆盖。destination 可以是 key 本身。
     *
     * @param destination destination
     * @param keys        keys
     * @return 结果集中的元素数量。
     */
    public Long sdiffstore(String destination, String... keys) {
        return jedis.sdiffstore(destination, keys);
    }

    //##########################################  Set  End ###################################################

    //##########################################  SortSet  Start ###################################################

    /**
     * 将一个 member 元素及其 score 值加入到有序集 key 当中。<br>
     * 如果 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。<br>
     * score 值可以是整数值或双精度浮点数。如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br>
     *
     * @param key    key
     * @param score  score
     * @param member member
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public Long zadd(String key, double score, String member) {
        return jedis.zadd(key, score, member);
    }

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。<br>
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。<br>
     * score 值可以是整数值或双精度浮点数。如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br>
     *
     * @param key          key
     * @param memberScores memberScores
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */

    public Long zadd(String key, Map<String, Double> memberScores) {
        return jedis.zadd(key, memberScores);
    }

    /**
     * 返回有序集 key 中，成员 member 的 score 值。
     *
     * @param key    key
     * @param member member
     * @return member 成员的 score 值
     */
    public Double zscore(String key, String member) {
        return jedis.zscore(key, member);
    }

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 。<br>
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。<br>
     * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。<br>
     *
     * @param key       key
     * @param increment increment
     * @param member    member
     * @return member 成员的新 score 值
     */
    public Double zincrby(String key, double increment, String member) {
        return jedis.zincrby(key, increment, member);
    }

    /**
     * 返回有序集 key 的基数。
     *
     * @param key key
     * @return 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0 。
     */
    public Long zcard(String key) {
        return jedis.zcard(key);
    }

    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
     *
     * @param key key
     * @param min min
     * @param max max
     * @return score 值在 min 和 max 之间的成员的数量。
     */
    public Long zcount(String key, double min, double max) {
        return jedis.zcount(key, min, max);
    }

    /**
     * 返回有序集 key 中，指定区间内的成员。<br>
     * 其中成员的位置按 score 值递增(从小到大)来排序。具有相同 score 值的成员按字典序(lexicographical order )来排列。<br>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。<br>
     * 超出范围的下标并不会引起错误。 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。<br>
     *
     * @param key   key
     * @param start start
     * @param stop  stop
     * @return 指定区间内，有序集成员的列表。
     */
    public Set<String> zrange(String key, long start, long stop) {
        return jedis.zrange(key, start, stop);
    }

    /**
     * 返回有序集 key 中，指定区间内的成员。<br>
     * 其中成员的位置按 score 值递减(从大到小)来排序。具有相同 score 值的成员按字典序(lexicographical order )来排列。<br>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。<br>
     * 超出范围的下标并不会引起错误。 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZREVRANGE 命令只是简单地返回一个空列表。 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。<br>
     *
     * @param key   key
     * @param start start
     * @param stop  stop
     * @return 指定区间内，有序集成员的列表。
     */
    public Set<String> zrevrange(String key, long start, long stop) {
        return jedis.zrevrange(key, start, stop);
    }

    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return jedis.zrevrangeByScore(key, max, min);
    }

    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return jedis.zrevrangeByScore(key, max, min);
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。 <br>
     * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。<br>
     *
     * @param key    key
     * @param member member
     * @return 如果 member 是有序集 key 的成员，返回 member 的排名。 如果 member 不是有序集 key 的成员，返回 nil 。
     */
    public Long zrank(String key, String member) {
        return jedis.zrank(key, member);
    }

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。 <br>
     * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。<br>
     *
     * @param key    key
     * @param member member
     * @return 如果 member 是有序集 key 的成员，返回 member 的排名。 如果 member 不是有序集 key 的成员，返回 nil 。
     */
    public Long zrevrank(String key, String member) {
        return jedis.zrevrank(key, member);
    }

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     *
     * @param key     key
     * @param members members
     * @return 被成功移除的成员的数量，不包括被忽略的成员。
     */
    public Long zrem(String key, String... members) {
        return jedis.zrem(key, members);
    }

    /**
     * 移除有序集 key 中，指定排名(rank)区间内的所有成员。<br>
     * 区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。<br>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。<br>
     *
     * @param key   key
     * @param start start
     * @param stop  stop
     * @return 被移除成员的数量。
     */
    public Long zremrangeByRank(String key, long start, long stop) {
        return jedis.zremrangeByRank(key, start, stop);
    }

    /**
     * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
     *
     * @param key key
     * @param min min
     * @param max max
     * @return 被移除成员的数量。
     */
    public Long zremrangeByScore(String key, double min, double max) {
        return jedis.zremrangeByScore(key, min, max);
    }


    //##########################################  SortSet  End ###################################################
}
