package cn.netinnet.nna.service;

import cn.netinnet.nna.dao.DemoInfoMapper;
import cn.netinnet.nna.domain.DemoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: DemoInfoService
 *
 * @version ${version}
 * @Description: DemoInfo 服务实现层
 * @author: ${user}
 * @date: ${date} ${time}
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
@Service
public class DemoInfoService {
    @Autowired
    private DemoInfoMapper demoInfoMapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    public int add(DemoInfo demoInfo) {
        return 0;
    }

    public int deleteByIds(String[] ids) {
        return 0;
    }

    public int update(DemoInfo demoInfo) {
        return 0;
    }

    public DemoInfo queryDemoInfoByObject(DemoInfo demoInfo) {
        return null;
    }

    public List<DemoInfo> queryDemoInfoList() {
        return null;
    }

    public void test(){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey4", "random1="+Math.random());
        System.out.println(valueOperations.get("mykey4"));
    }

    //keyGenerator="myKeyGenerator"
    @Cacheable(value="demoInfo") //缓存,这里没有指定key.
    public DemoInfo queryDemoInfoById(int id) {
        System.err.println("DemoInfoServiceImpl.queryDemoInfoById()=========从数据库中进行获取的....id="+id);
        return demoInfoMapper.queryDemoInfoById(id);
    }

    @CacheEvict(value="demoInfo")
    public void deleteFromCache(long id) {
        System.out.println("DemoInfoServiceImpl.delete().从缓存中删除.");
    }
}
