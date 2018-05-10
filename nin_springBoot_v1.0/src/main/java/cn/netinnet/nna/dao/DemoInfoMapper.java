package cn.netinnet.nna.dao;

import cn.netinnet.nna.domain.DemoInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DemoInfoMapper {
    int add(DemoInfo demoInfo);

    int deleteByIds(String[] ids);

    int update(DemoInfo demoInfo);

    DemoInfo queryDemoInfoByObject(DemoInfo demoInfo);

    DemoInfo queryDemoInfoById(int id);

    List<DemoInfo> queryDemoInfoList();

}