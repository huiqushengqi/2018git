package cn.netinnet.nna.service;

import cn.netinnet.nna.dao.LearnResourceMapper;
import cn.netinnet.nna.domain.LearnResource;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:    LearnReSourceService
 * @Description:  LearnResource 服务实现层
 *
 * @author         ${user}
 * @version        V1.0
 * @Date           ${date} ${time}
 *
 **/
@Service
public class LearnResourceService {

    @Autowired
    LearnResourceMapper learnResourceMapper;

    public int add(LearnResource learnResource) {
        return this.learnResourceMapper.add(learnResource);
    }

    public int update(LearnResource learnResource) {
        return this.learnResourceMapper.update(learnResource);
    }

    public int deleteByIds(String[] ids) {
        return this.learnResourceMapper.deleteByIds(ids);
    }

    public LearnResource queryLearnResouceById(Long id) {
        return this.learnResourceMapper.queryLearnResouceById(id);
    }

    public List<LearnResource> queryLearnResouceList(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.learnResourceMapper.queryLearnResouceList(params);
    }
}
