package com.yangya.service.impl;

import com.yangya.config.RedisConfig;
import com.yangya.mapper.BrandMapper;
import com.yangya.model.PmsBrand;
import com.yangya.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsBrandService实现类
 * Created by macro on 2019/4/19.
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public int create(PmsBrand brand) {
        return brandMapper.insert(brand);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#id")
    @Override
    public int update(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateById(brand);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#id")
    @Override
    public int delete(Long id) {
        return brandMapper.deleteById(id);
    }

    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#id", unless = "#result==null")
    @Override
    public PmsBrand getItem(Long id) {
        return brandMapper.selectById(id);
    }

    @Override
    public List<PmsBrand> list(Integer pageNum, Integer pageSize) {
        /*PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new PmsBrandExample());*/
        return null;
    }

    @Override
    public List<PmsBrand> ListAll() {
        return brandMapper.selectList(null);
    }


}
