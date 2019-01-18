package cn.xiaoyu.service.tally.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.entity.tally.ConsumeType;
import cn.xiaoyu.service.tally.ConsumeTypeService;
 
@Service
public class ConsumeTypeServiceImpl extends Base implements ConsumeTypeService {

	@Override
	public List<ConsumeType> list() {
		return consumeTypeMapper.list();
	}

	 
}
