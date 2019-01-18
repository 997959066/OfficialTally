package cn.xiaoyu.service.tally;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.entity.tally.Tally;

/**
 * tally Service类
 */
public interface TallyService {
	
	/**
	 * 分页查询方法
	 */
	public PageBean<Tally> listByPage(Tally tally,Integer pageNo,Integer pageSize) ;

	int append(Tally tally);

	int delete(Integer id,Integer userId);

	int update(Tally tally);

	List<Tally> list(Tally tally);

	/** 计算一段时间内消费总和*/
	Tally theTotalAmount(String startTime, String endTime,String used,Integer userId);
	/**2018.12.25查询一段时间分类钱数*/
	HashMap<String,Object> categoricalConsumption(int year,int month);
	/**年分类消费统计*/
	HashMap<String,Object> yearCateInfo(int year);

	/**每月消费汇总查询（对象格式）*/
	public List<HashMap<String, Object>> theMonthlyConsumption(Integer userId,int year);

	/**
	 * 导入明细消费数据
	 * 从页面上导入数据，当数据库数据不在时通过Execl备份
	 */
	public int importData(Integer userId,HttpServletRequest request)throws ServletException, IOException ;
	
	/**
	 * 描述 : 全信息导出
	 */
	public void yearAllExport(Integer userId,HttpServletResponse response) throws ScriptException;
}
