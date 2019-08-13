package com.mini.web.test.dao.base;

import com.mini.jdbc.BasicsDao;
import com.mini.jdbc.SQLBuilder;
import com.mini.jdbc.util.Paging;
import com.mini.web.test.entity.Region;
import com.mini.web.test.entity.mapper.RegionMapper;
import java.util.List;

/**
 * BaseRegionDao.java 
 * @author xchao 
 */
public interface BaseRegionDao extends BasicsDao {
  RegionMapper getRegionMapper();

  /**
   * 添加实体信息 
   * @param region 实体信息 
   * @return 执行结果 
   */
  default int insert(Region region) {
    return execute(new SQLBuilder() {{ 
    	insert_into(Region.TABLE);
    	// 地区码/地区ID 
    	values(Region.ID);
    	params(region.getId());
    	// 地区名称 
    	values(Region.NAME);
    	params(region.getName());
    	// 地区ID列表 
    	values(Region.ID_URI);
    	params(region.getIdUri());
    	// 地区名称列表 
    	values(Region.NAME_URI);
    	params(region.getNameUri());
    	// 上级地区ID 
    	values(Region.REGION_ID);
    	params(region.getRegionId());
    }});
  }

  /**
   * 修改实体信息 
   * @param region 实体信息 
   * @return 执行结果 
   */
  default int update(Region region) {
    return execute(new SQLBuilder() {{ 
    	update(Region.TABLE);
    	// 地区码/地区ID 
    	set("%s = ?", Region.ID);
    	params(region.getId());
    	// 地区名称 
    	set("%s = ?", Region.NAME);
    	params(region.getName());
    	// 地区ID列表 
    	set("%s = ?", Region.ID_URI);
    	params(region.getIdUri());
    	// 地区名称列表 
    	set("%s = ?", Region.NAME_URI);
    	params(region.getNameUri());
    	// 上级地区ID 
    	set("%s = ?", Region.REGION_ID);
    	params(region.getRegionId());
    	// 地区码/地区ID 
    	where("%s = ?", Region.ID);
    	params(region.getId());
    }});
  }

  /**
   * 删除实体信息 
   * @param region 实体信息 
   * @return 执行结果 
   */
  default int delete(Region region) {
    return execute(new SQLBuilder() {{ 
    	delete().from(Region.TABLE);
    	// 地区码/地区ID 
    	where("%s = ?", Region.ID);
    	params(region.getId());
    }});
  }

  /**
   * 根据ID删除实体信息 
   * @param id 地区码/地区ID 
   * @return 执行结果 
   */
  default int deleteById(int id) {
    return execute(new SQLBuilder() {{ 
    	delete().from(Region.TABLE);
    	// 地区码/地区ID 
    	where("%s = ?", Region.ID);
    	params(id);
    }});
  }

  /**
   * 根据ID查询实体信息 
   * @param id 地区码/地区ID 
   * @return 实体信息 
   */
  default Region queryById(int id) {
    return queryOne(new SQLBuilder() {{ 
    	RegionMapper.init(this);
    	// 地区码/地区ID 
    	where("%s = ?", Region.ID);
    	params(id);
    }}, getRegionMapper());
  }

  /**
   * 查询所有实体信息 
   * @return 实体信息列表 
   */
  default List<Region> queryAll() {
    return query(new SQLBuilder(){{
    	RegionMapper.init(this);
    }}, getRegionMapper());
  }

  /**
   * 查询所有实体信息 
   * @param paging 分布工具
   * @return 实体信息列表 
   */
  default List<Region> queryAll(Paging paging) {
    return query(paging, new SQLBuilder(){{
    	RegionMapper.init(this);
    }}, getRegionMapper());
  }
}
