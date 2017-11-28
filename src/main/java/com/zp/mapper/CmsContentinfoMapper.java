package com.zp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zp.entity.CmsContentinfo;
@Mapper
public interface CmsContentinfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(CmsContentinfo record);

    int insertSelective(CmsContentinfo record);

    CmsContentinfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CmsContentinfo record);

    int updateByPrimaryKeyWithBLOBs(CmsContentinfo record);

    int updateByPrimaryKey(CmsContentinfo record);
    
    List<CmsContentinfo> queryList(Map<String, Object> map);
    
    List<CmsContentinfo> queryAll(Map<String, Object> map);
}