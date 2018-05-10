package cn.netinnet.nna.dao;


import cn.netinnet.nna.domain.LearnResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:    LearnResourceMapper
 * @Description:  LearnResource Dao接口层
 *
 * @author         ${user}
 * @version        V1.0
 * @Date           ${date} ${time}
 *
 **/
@Mapper
@Repository
public interface LearnResourceMapper {
    int add(LearnResource learnResource);
    int update(LearnResource learnResource);
    int deleteByIds(String[] ids);
    LearnResource queryLearnResouceById(Long id);
    public List<LearnResource> queryLearnResouceList(Map<String, Object> params);
}
