package cn.xiaoyu.service.tally.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.common.CommonUtils;
import cn.xiaoyu.common.PageBean;
import cn.xiaoyu.entity.tally.ConsumeType;
import cn.xiaoyu.entity.tally.Income;
import cn.xiaoyu.entity.tally.Summary;
import cn.xiaoyu.entity.tally.Tally;
import cn.xiaoyu.service.tally.TallyService;
import cn.xiaoyu.util.DateUtil;
import cn.xiaoyu.util.TranUtil;

/**
 * tally Service类
 */
@Service
public class TallyServiceImpl extends Base implements TallyService {

	 //分页查询方法
	public PageBean<Tally> listByPage(Tally tally, Integer pageNo, Integer pageSize) {
		CommonUtils.checkNull(new String[] { "pageNo", "pageSize" }, pageNo, pageSize);
		List<Tally> result = this.list(tally);
		PageHelper.startPage(pageNo, pageSize);
		PageBean<Tally> pageData = new PageBean<Tally>(pageNo, pageSize, result.size());
		pageData.setList(list(tally));
		return pageData;
	}

	public List<Tally> list(Tally tally) {
		return tallyMapper.list(tally);
	}

	public int append(Tally tally) {
		int appendId = tallyMapper.append(tally);
		if (appendId > 0) {
			summaryService.dailyConsumptionYear(null, tally.getUserId());
		}
		return appendId;
	}

	public int delete(Integer id, Integer userId) {
		int appendId = tallyMapper.delete(id, userId);
		if (appendId > 0) {
			summaryService.dailyConsumptionYear(null, userId);
		}
		return appendId;
	}

	public int update(Tally tally) {
		int appendId = tallyMapper.update(tally);
		if (appendId > 0) {
			summaryService.dailyConsumptionYear(null, tally.getUserId());
		}
		return appendId;
	}

	@Override
	public Tally theTotalAmount(String startTime, String endTime, String used, Integer userId) {
		return tallyMapper.theTotalAmount(startTime, endTime, null, userId);
	}

	// 月度综合报表（月收入，月支出，月每日平均消费）
	@Override
	public List<HashMap<String, Object>> theMonthlyConsumption(Integer userId, int year) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int m = 1; m <= 12; m++) {
			Map<String, Date> beMoTime = DateUtil.betweenMonthDateTime(year, m);
			Date time = beMoTime.get("firstDate");
			LocalDate localDate = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date newDate = java.sql.Date.valueOf(localDate);
			list.add(summaryMapper.monthlyComprehensive(newDate, userId));
		}
		return list;
	}

	/**
	 * 导入明细消费数据 从页面上导入数据，当数据库数据不在时通过Execl备份
	 */
	@Override
	public int importData(Integer userId, HttpServletRequest request) throws ServletException, IOException {
		String filePath = uploadOrDownloadService.uploadFile(request);
		int returnId = 0;
		// 遍历扇叶 明细消费
		Workbook workbook = TranUtil.checkFile(filePath);
		Integer sheetNum = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			if ("明细消费".equals(workbook.getSheetName(i))) {
				Sheet sheet = workbook.getSheetAt(i);
				// 取得文件头个数（列数）
				int headNum = sheet.getRow(0).getLastCellNum();
				// 得到总行数，第二行开始操作
				int rowNumLast = sheet.getLastRowNum();
				for (int rownum = 1; rownum <= rowNumLast; rownum++) {
					Row currow = (Row) sheet.getRow(rownum);
					if (null != currow) {
						Tally tally = new Tally();
						for (int lie = 0; lie < headNum; lie++) {
							if (lie == 0) {
								String createDate = TranUtil.getCellFormatValue(currow.getCell(lie));
								tally.setCreateDate(createDate);
							} else if (lie == 1) {
								String used = TranUtil.getCellFormatValue(currow.getCell(lie));
								tally.setUsed(used);
							} else if (lie == 2) {
								Cell cell = currow.getCell(lie);
								BigDecimal b = new BigDecimal(cell.getStringCellValue());
								tally.setHowMuch(b);
							}
						}
						tally.setUserId(userId);
						returnId = tallyMapper.append(tally);
					}
				}

			}
		}
		if (returnId > 0) {
			summaryService.dailyConsumptionYear(null, userId);
		}
		return returnId;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 描述 : 年度支出 yearAllExport中分离
	 * 
	 */
	public void annualExpenditureExport(int year, HSSFCellStyle style, HSSFWorkbook wb, Integer userId) {
		// 年消费
		HSSFSheet sheet5 = wb.createSheet(year + "年消费总钱数");
		sheet5.setDefaultColumnWidth(12);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow row5 = sheet5.createRow(0);
		row5.createCell(0).setCellValue("年消费");
		row5.setRowStyle(style);
		HSSFRow row = sheet5.createRow(1);
		Tally tallyObject = theTotalAmount(year + "-01-01", year + "-12-31", null, userId);
		if (tallyObject != null) {
			row.createCell(0).setCellValue(tallyObject.getHowMuch().toString());
		}
	}

	/**
	 * 月汇总 yearAllExport中分离
	 */
	public void monthSummary(Integer userId, int year, HSSFCellStyle style, HSSFWorkbook wb) {
		HSSFSheet sheet = wb.createSheet(year + "年度月汇总");// 每月消费
		sheet.setDefaultColumnWidth(12);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFRow row5 = sheet.createRow(0);
		row5.createCell(0).setCellValue("年总消费");
		row5.setRowStyle(style);
		Tally tallyObject = theTotalAmount(year + "-01-01", year + "-12-31", null, userId);
		if (tallyObject != null) {
			row5.createCell(1).setCellValue(tallyObject.getHowMuch().toString());
		}

		HSSFRow row1 = sheet.createRow(1);// 第一行表头
		row1.createCell(0).setCellValue("月数");
		row1.createCell(1).setCellValue("月收入");
		row1.createCell(2).setCellValue("月消费");
		row1.createCell(3).setCellValue("月每日平均消费");
		row1.setRowStyle(style);
		for (int m = 1; m <= 12; m++) {// 每月消费
			Map<String, Date> beMoTime = DateUtil.betweenMonthDateTime(year, m);
			Date time = beMoTime.get("firstDate");
			LocalDate localDate = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date newDate = java.sql.Date.valueOf(localDate);
			HashMap<String, Object> mapObject = summaryMapper.monthlyComprehensive(newDate, userId);
			if (mapObject != null) {
				HSSFRow row01 = sheet.createRow(m + 1);
				row01.createCell(0)
						.setCellValue(mapObject.get("month") != null ? mapObject.get("month").toString() : "0" + "月");
				row01.createCell(1)
						.setCellValue(mapObject.get("money") != null ? mapObject.get("money").toString() : "0");
				row01.createCell(2)
						.setCellValue(mapObject.get("spending") != null ? mapObject.get("spending").toString() : "0");// 支出
				row01.createCell(3)
						.setCellValue(mapObject.get("average") != null ? mapObject.get("average").toString() : "0");// 平均
			}
		}
	}

	/**
	 * 明细数据 yearAllExport中抽离
	 * 
	 */
	public void theDetailExport(int year, HSSFCellStyle style, HSSFWorkbook wb) {
		// 每次明细消费数据
		List<String> headerList2 = new ArrayList<>();
		headerList2.add("日期");
		headerList2.add("用途");
		headerList2.add("钱数");
		String[] header2 = new String[headerList2.size()];
		headerList2.toArray(header2);
		HSSFSheet sheet = wb.createSheet("明细消费");
		sheet.setDefaultColumnWidth(12);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow row2 = sheet.createRow(0);
		for (int i = 0; i < header2.length; i++) {
			row2.createCell(i).setCellValue(header2[i]);
			row2.setRowStyle(style);
		}
		List<Tally> tall = this.list(new Tally());
		if (tall.size() != 0) {
			for (int i = 0; i < tall.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("日期", tall.get(i).getCreateDate().substring(0, 10));
				map.put("用途", tall.get(i).getUsed());
				map.put("钱数", tall.get(i).getHowMuch().toString());
				HSSFRow row11 = sheet.createRow(i + 1);
				JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
				for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
					for (int j = 0; j < headerList2.size(); j++) {
						if (entry.getKey().equals(headerList2.get(j))) {
							String neirong = jsonObject.getString(headerList2.get(j));
							row11.createCell(j).setCellValue(neirong);
						}
					}
				}
			}
		}
	}

	/**
	 * 每日消费数据 yearAllExport中抽离
	 * 
	 */
	public void dailyConsumptionSun(Integer userId, int year, HSSFCellStyle style, HSSFWorkbook wb) {
		// 每次明细消费数据
		HSSFSheet sheet = wb.createSheet(year + "年度每日消费钱数");
		sheet.setDefaultColumnWidth(12);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("日期");
		row1.createCell(1).setCellValue("当天消费");
		row1.setRowStyle(style);
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-01-01");
			List<Summary> yearSummary = summaryMapper.listByCountDate(date, userId);
			for (int j = 0; j < yearSummary.size(); j++) {
				HSSFRow r = sheet.createRow(j + 1);
				r.createCell(0)
						.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(yearSummary.get(j).getCountDate()));
				r.createCell(1).setCellValue(yearSummary.get(j).getExpense().toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 描述 : 年度收入详情导出
	 * 
	 */
	public void incomeDetailsExport(int year, HSSFCellStyle style, HSSFWorkbook wb, Integer userId) {
		// 年收入
		HSSFSheet sheet6 = wb.createSheet(year + "年度收入详情");
		sheet6.setDefaultColumnWidth(12);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow row6 = sheet6.createRow(0);
		row6.createCell(0).setCellValue("收入来源");
		row6.createCell(1).setCellValue("年");
		row6.createCell(2).setCellValue("月");
		row6.createCell(3).setCellValue("钱数");
		row6.setRowStyle(style);
		Income income = new Income();
		income.setYear(year);
		List<Income> listIncome = incomeMapper.incomeDetails(income);
		if (listIncome.size() != 0) {
			for (int i = 0; i < listIncome.size(); i++) {
				HSSFRow row = sheet6.createRow(i + 1);
				row.createCell(0).setCellValue(listIncome.get(i).getSource());
				row.createCell(1).setCellValue(String.valueOf(listIncome.get(i).getYear()));
				row.createCell(2).setCellValue(String.valueOf(listIncome.get(i).getMonth()));
				row.createCell(3).setCellValue(String.valueOf(listIncome.get(i).getMoney()));
			}
		}
	}

	/**
	 * 描述 : 年度收纯收益导出
	 * 
	 * @throws ScriptException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void netIncomeExport(int year, HSSFCellStyle style, HSSFWorkbook wb, Integer userId) {
		// 年收入
		try {
			HSSFSheet sheet6 = wb.createSheet(year + "年度纯收益");
			sheet6.setDefaultColumnWidth(12);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HashMap<String, Object> result = incomeService.netIncome(year, userId);
			HSSFRow row61 = sheet6.createRow(0);
			row61.createCell(0).setCellValue(year + "年纯收入");
			row61.createCell(1).setCellValue(result.get("sum").toString());
			HSSFRow row6 = sheet6.createRow(1);
			row6.createCell(0).setCellValue("月");
			row6.createCell(1).setCellValue("纯利润");
			row6.setRowStyle(style);
			Income income = new Income();
			income.setYear(year);
			List<HashMap<String, String>> listIncome = (List<HashMap<String, String>>) result.get("list");
			if (listIncome.size() != 0) {
				for (int i = 0; i < listIncome.size(); i++) {
					HSSFRow row = sheet6.createRow(i + 2);
					row.createCell(0).setCellValue(String.valueOf(listIncome.get(i).get("name")));// 月
					row.createCell(1).setCellValue(String.valueOf(listIncome.get(i).get("value")));
				}
			}
		} catch (ScriptException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 描述 : 全信息导出
	 * 
	 * @throws ScriptException
	 * 
	 * @throws ParseException
	 *             2018.12.21增情纯收入的导出，合并总消费在月汇总中
	 * 
	 */
	public void yearAllExport(Integer userId, HttpServletResponse response) throws ScriptException {
		// 创建
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		int year = Integer.valueOf(DateUtil.getSysYear()).intValue() - 1;  //当前年 - 1
		// 明细消费数据
		this.theDetailExport(year, style, wb);
		// 年度每天消费钱数数
		this.dailyConsumptionSun(userId, year, style, wb);
		// 月汇总
		this.monthSummary(userId, year, style, wb);
		// 收入详情
		this.incomeDetailsExport(year, style, wb, userId);
		// 收入纯利润
		this.netIncomeExport(year, style, wb, userId);

		////////////////////////////////////////////////////
		TranUtil.outputFile(response, wb,
				year + "年综合消费数据" + new SimpleDateFormat("yyyy-MM-dd HH：mm：ss").format(new Date()) + "导出数据");
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public HashMap<String, Object> categoricalConsumption(int year, int month) {
		HashMap<String, Object> s = new HashMap<>();
		Map<String, String> timee = DateUtil.betweenMonthTime(year, month);
		List<Tally> tallyList = tallyMapper.categoricalConsumption(timee.get("startTime").toString(),
				timee.get("endTime").toString(), getUser().getId(), null);
		List<HashMap<String, String>> mapList = new ArrayList<>();
		List<String> l = new ArrayList<>();
		 BigDecimal totalAmount= BigDecimal.ZERO;
		 for (int i = 0; i < tallyList.size(); i++) {
			 totalAmount=totalAmount.add(tallyList.get(i).getHowMuch());
			 l.add(tallyList.get(i).getTypeName());
				HashMap<String, String> map = new HashMap<>();
				map.put("name", tallyList.get(i).getTypeName());
				map.put("value", tallyList.get(i).getHowMuch().toString());
				mapList.add(map);
		}
		s.put("totalAmount", totalAmount);
		s.put("typeList", mapList);
		s.put("typeName", l);
		return s;
	}

	//一年所有分类数据
	@Override
	public HashMap<String, Object> yearCateInfo(int year) {
		HashMap<String, Object> ob = new HashMap<>();
		// 按类去查 把类全都查出来
		List<String> typeName = new ArrayList<>();
		List<ConsumeType> listConsumeType = consumeTypeService.list();
		List<HashMap<String, Object>> listseries = new ArrayList<>();
		for (int j = 0; j < listConsumeType.size(); j++) {
			HashMap<String, Object> obj = new HashMap<>();
			typeName.add(listConsumeType.get(j).getTypeName());
			obj.put("name", listConsumeType.get(j).getTypeName());
			List<String> howMuch = new ArrayList<>();
			// 一个类型下 12个月数据
			for (int i = 1; i <= 12; i++) {
				Map<String, String> timee = DateUtil.betweenMonthTime(year, i); // 1
				List<Tally> tallyList = tallyMapper.categoricalConsumption(timee.get("startTime").toString(),
						timee.get("endTime").toString(), getUser().getId(), listConsumeType.get(j).getId());
				howMuch.add(tallyList.size() != 0 ? tallyList.get(0).getHowMuch().toString() : "0");
			}
			obj.put("data", howMuch);
			obj.put("type", "bar"); // 前端配合
			obj.put("stack", "总量"); // 前端配合
			HashMap<String, Object> objs = new HashMap<>();
			HashMap<String, Object> objs2 = new HashMap<>();
			objs2.put("show", true);
			objs2.put("position", "insideRight");
			objs.put("normal", objs2);
			obj.put("label", objs); // 前端配合
			// 存入大集合
			listseries.add(obj);
		}
		HashMap<String, Object> objlegend = new HashMap<>();
		objlegend.put("data", typeName);
		ob.put("legend", objlegend);
		ob.put("series", listseries);
		return ob;
	}
}
