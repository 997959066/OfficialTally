package cn.xiaoyu.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	/**
	 * 描述:
	 * @author  xiaoyu.zhang
	 * 日期:2018-12-20 删除多余方法
	*/ 
	
	public static List<String> stringList(String userIdList,String split){
		List<String> idsList = new ArrayList<String>();
		if (userIdList != null) {
			String[] ids = userIdList.split(split);
			for (String id : ids) {
				idsList.add(id);
			}
		}
		return idsList;
	}
}
