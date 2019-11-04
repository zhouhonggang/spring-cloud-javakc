import com.zhou.javakc.component.data.redis.DataRedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-24 08:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DataRedisApplication.class})
public class RedisClient {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test()
    {
//        redisTemplate....公共的方法命令
//        redisTemplate.opsForValue();
//        redisTemplate.opsForHash();
//        redisTemplate.opsForSet();
//        redisTemplate.opsForList();
//        redisTemplate.opsForZSet();

//        boolean flag = redisTemplate.delete("b");
//        System.out.println( flag );
        //首先获取操作String的对象接口
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

//        valueOperations.set("b", "123");
//        String val = valueOperations.get("b");
//        System.out.println( val );

//        valueOperations.set("key", "value", 1000);

//        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("color", "蓝色");
//        map.put("name", "奥迪");
//        hash.putAll("car3", map);
    }

}
