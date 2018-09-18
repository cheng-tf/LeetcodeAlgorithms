package redisClientJedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

public class JedisTest {
	
	public static void main(String[] args) {
//		Jedis jedis = new Jedis("39.106.30.172",6379);//"39.106.30.172",6379
//		jedis.set("Beijing", "Capital");
//		System.out.println(jedis.get("hello"));
//		System.out.println(jedis.get("Beijing"));
//		jedis.lpush("mylist", "AAA","BBB","CCC");
//		jedis.lset("mylist", 0, "BUPT");
//		System.out.println(jedis.lindex("mylist", 1));
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		JedisPool pool 	= new JedisPool(config,"39.106.30.172",6379);
		Jedis jedis = pool.getResource();
		jedis.auth("zhao12");
		Map<String,String> map = jedis.hgetAll("myhash");
		System.out.println(jedis.get("str:1"));
		
		System.out.println(map.toString());
		jedis.close();
		
	}
}
