package pers.darren.redis.lettuce;

import static io.lettuce.core.Range.create;
import static io.lettuce.core.RedisClient.create;
import static io.lettuce.core.cluster.RedisClusterURIUtil.toRedisURIs;
import static java.util.UUID.randomUUID;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

public class LettuceRedisClient {

    private static final String KEY = "Key";
    private static final String IDE_TYPE = "IDEType";
    private static final String USER_TOKEN = "USER_TOKEN";
    private static final String PERSONAL_INFO = "PersonalInfo";
    private static final String LANGUAGE_TYPE = "LanguageType";
    private static final String OPERATION_SYSTEM = "OperationSystem";

    public static void main(final String[] args) {
        System.out.println("============================单机模式开始============================");
        standalone();
        System.out.println("============================单机模式结束============================");
        System.out.println();

        System.out.println("============================哨兵模式开始============================");
        sentinel();
        System.out.println("============================哨兵模式结束============================");
        System.out.println();

        System.out.println("============================集群模式开始============================");
        cluster();
        System.out.println("============================集群模式结束============================");
        System.out.println();
    }

    /**
     * 连接并操作单机模式的Redis
     *
     * @CreatedBy Darren Luo
     * @CreatedTime May 27, 2021 11:51:45 AM
     */
    private static final void standalone() {
        /**
         * <pre>
         * URI syntax
         * Redis Standalone
         * redis://[[username:]password@]host[:port][/database][?[timeout=timeout[d|h|m|s|ms|us|ns]]
         * Redis Standalone (SSL)
         * rediss://[[username:]password@]host[:port][/database][?[timeout=timeout[d|h|m|s|ms|us|ns]]
         * </pre>
         */
        redisOperationExample("redis://abcd1234@127.0.0.1:6379/0");
    }

    /**
     * 连接并操作哨兵模式的Redis
     *
     * @CreatedBy Darren Luo
     * @CreatedTime May 28, 2021 5:32:31 PM
     */
    private static final void sentinel() {
        /**
         * <pre>
         * URI syntax
         * Redis Sentinel
         * redis-sentinel://[[username:]password@]host1[:port1][,host2[:port2]][,hostN[:portN]][/database][?[timeout=timeout[d|h|m|s|ms|us|ns]][&sentinelMasterId=sentinelMasterId]]
         * </pre>
         */
        redisOperationExample("redis-sentinel://abcd1234@127.0.0.1:26380,127.0.0.1:26381,127.0.0.1:26382/0#mymaster");
    }

    /**
     * 连接并操作集群模式的Redis
     *
     * @CreatedBy Darren Luo
     * @CreatedTime May 28, 2021 5:32:48 PM
     */
    private static final void cluster() {
        /**
         * <pre>
         * URI syntax
         * Redis Cluster
         * redis://[[username:]password@]host1[:port1][,host2[:port2]][,hostN[:portN]]
         * </pre>
         */
        redisClusterOperationExample("redis://127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003,127.0.0.1:7004");
    }

    private static final void redisOperationExample(final String redisUri) {
        final RedisClient redisClient = create(redisUri);
        final StatefulRedisConnection<String, String> connection = redisClient.connect();
        final RedisCommands<String, String> syncCommands = connection.sync();

        // String数据类型
        System.out.println(syncCommands.get("spring:session:id"));
        syncCommands.set(KEY, "Hello World!");
        System.out.println(syncCommands.get(KEY));
        syncCommands.set(KEY, "Hello, Redis!");
        System.out.println(syncCommands.get(KEY));
        syncCommands.setex(USER_TOKEN, 30 * 60, randomUUID().toString());
        System.out.println(syncCommands.get(USER_TOKEN));

        // Hash数据类型
        syncCommands.del(PERSONAL_INFO);
        final Map<String, String> map = new HashMap<>();
        map.put("Name", "Darren Luo");
        map.put("IdNo", "432524199202084911");
        map.put("Age", "28");
        map.put("Gender", "Male");
        map.put("Phone", "15111184742");
        syncCommands.hset(PERSONAL_INFO, map);
        System.out.println(syncCommands.hget(PERSONAL_INFO, "Name"));
        System.out.println(syncCommands.hget(PERSONAL_INFO, "IdNo"));
        System.out.println(syncCommands.hgetall(PERSONAL_INFO));

        // List数据类型
        syncCommands.del(LANGUAGE_TYPE);
        syncCommands.lpush(LANGUAGE_TYPE, "C", "C++", "Java", "C#", "Python", "PHP", "Kotlin");
        Long length = syncCommands.llen(LANGUAGE_TYPE);
        System.out.println(syncCommands.lrange(LANGUAGE_TYPE, 0, 2));
        System.out.println(syncCommands.lrange(LANGUAGE_TYPE, 0, length));
        syncCommands.rpush(LANGUAGE_TYPE, "Javascript", "Go", "Ruby", "Visual Basic");
        length = syncCommands.llen(LANGUAGE_TYPE);
        System.out.println(syncCommands.lrange(LANGUAGE_TYPE, 0, length));

        // Set数据类型
        syncCommands.del(OPERATION_SYSTEM);
        syncCommands.sadd(OPERATION_SYSTEM, "Windows", "Linux", "MacOS", "HarmonyOS", "HarmonyOS");
        System.out.println(syncCommands.smembers(OPERATION_SYSTEM));
        System.out.println(syncCommands.scard(OPERATION_SYSTEM));
        System.out.println(syncCommands.sismember(OPERATION_SYSTEM, "HarmonyOS"));

        // ZSet数据类型（Sorted Set：有序集合）
        syncCommands.del(IDE_TYPE);
        syncCommands.zadd(IDE_TYPE, 0, "Eclipse");
        syncCommands.zadd(IDE_TYPE, 1, "IDEA");
        syncCommands.zadd(IDE_TYPE, 2, "VSCode");
        syncCommands.zadd(IDE_TYPE, 3, "Visual Studio");
        syncCommands.zadd(IDE_TYPE, 4, "MyEclipse");
        syncCommands.zadd(IDE_TYPE, 5, "MyEclipse");
        length = syncCommands.zcard(IDE_TYPE);
        System.out.println(syncCommands.zrange(IDE_TYPE, 0, 2));
        System.out.println(syncCommands.zrange(IDE_TYPE, 0, length));
        System.out.println(syncCommands.zrangebyscore(IDE_TYPE, create(1, 3)));

        connection.close();
        redisClient.shutdown();
    }

    private static final void redisClusterOperationExample(final String redisUri) {
        final List<RedisURI> redisURIList = toRedisURIs(URI.create(redisUri));
        final RedisClusterClient redisClusterClient = RedisClusterClient.create(redisURIList);
        final StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();
        final RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();

        // String数据类型
        System.out.println(syncCommands.get("spring:session:id"));
        syncCommands.set(KEY, "Hello World!");
        System.out.println(syncCommands.get(KEY));
        syncCommands.set(KEY, "Hello, Redis!");
        System.out.println(syncCommands.get(KEY));
        syncCommands.setex(USER_TOKEN, 30 * 60, randomUUID().toString());
        System.out.println(syncCommands.get(USER_TOKEN));

        // Hash数据类型
        syncCommands.del(PERSONAL_INFO);
        final Map<String, String> map = new HashMap<>();
        map.put("Name", "Darren Luo");
        map.put("IdNo", "432524199202084911");
        map.put("Age", "28");
        map.put("Gender", "Male");
        map.put("Phone", "15111184742");
        syncCommands.hset(PERSONAL_INFO, map);
        System.out.println(syncCommands.hget(PERSONAL_INFO, "Name"));
        System.out.println(syncCommands.hget(PERSONAL_INFO, "IdNo"));
        System.out.println(syncCommands.hgetall(PERSONAL_INFO));

        // List数据类型
        syncCommands.del(LANGUAGE_TYPE);
        syncCommands.lpush(LANGUAGE_TYPE, "C", "C++", "Java", "C#", "Python", "PHP", "Kotlin");
        Long length = syncCommands.llen(LANGUAGE_TYPE);
        System.out.println(syncCommands.lrange(LANGUAGE_TYPE, 0, 2));
        System.out.println(syncCommands.lrange(LANGUAGE_TYPE, 0, length));
        syncCommands.rpush(LANGUAGE_TYPE, "Javascript", "Go", "Ruby", "Visual Basic");
        length = syncCommands.llen(LANGUAGE_TYPE);
        System.out.println(syncCommands.lrange(LANGUAGE_TYPE, 0, length));

        // Set数据类型
        syncCommands.del(OPERATION_SYSTEM);
        syncCommands.sadd(OPERATION_SYSTEM, "Windows", "Linux", "MacOS", "HarmonyOS", "HarmonyOS");
        System.out.println(syncCommands.smembers(OPERATION_SYSTEM));
        System.out.println(syncCommands.scard(OPERATION_SYSTEM));
        System.out.println(syncCommands.sismember(OPERATION_SYSTEM, "HarmonyOS"));

        // ZSet数据类型（Sorted Set：有序集合）
        syncCommands.del(IDE_TYPE);
        syncCommands.zadd(IDE_TYPE, 0, "Eclipse");
        syncCommands.zadd(IDE_TYPE, 1, "IDEA");
        syncCommands.zadd(IDE_TYPE, 2, "VSCode");
        syncCommands.zadd(IDE_TYPE, 3, "Visual Studio");
        syncCommands.zadd(IDE_TYPE, 4, "MyEclipse");
        syncCommands.zadd(IDE_TYPE, 5, "MyEclipse");
        length = syncCommands.zcard(IDE_TYPE);
        System.out.println(syncCommands.zrange(IDE_TYPE, 0, 2));
        System.out.println(syncCommands.zrange(IDE_TYPE, 0, length));
        System.out.println(syncCommands.zrangebyscore(IDE_TYPE, create(1, 3)));

        connection.close();
        redisClusterClient.shutdown();
    }
}