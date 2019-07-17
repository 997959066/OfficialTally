var networkProtocol="http://";
//var ip="10.45.70.31";
var ip="localhost";
var port=":8080";
// var project="/OfficialTally";
//var project="";  //jar 不指定
var url=networkProtocol+ip+port/*+project*/;

//tally记账模块
var saveUrl=url+"/tally/append";
var findUrl=url+"/tally/list";
var updateUrl=url+"/tally/update";
var deleteUrl=url+"/tally/delete";
var yearCateInfoUrl=url+"/tally/yearCateInfo";
//统计一段时间钱数
var theTotalAmountUrl=url+"/tally/theTotalAmount";
//每月消费钱数
var theMonthlyConsumptionUrl=url+"/tally/theMonthlyConsumption";
//分页list
var listByPageUrl=url+"/tally/listByPage";
//当前年全部信息导出Execl
var yearAllExport=url+"/tally/yearAllExport";
//上传明细消费
var  execlUploadUrl=url+"/tally/importData";
//按月分类消费
var categoricalConsumptionUrl=url+"/tally/categoricalConsumption";
//报表//每日消费
var summaryListUrl=url+"/summary/list";
//月综合报表
var monthlyComprehensiveUrl=url+"/summary/monthlyComprehensive";
//季节综合报表
var seasonComprehensiveUrl=url+"/summary/seasonComprehensive";
//上周每天消费消费
var annualWeeklyReportUrl=url+"/summary/annualWeeklyReport";

//-------------------------------------------------------------------
//收入页
var incomeDetailsUrl=url+"/income/incomeDetails";
var saveIncomeUrl=url+"/income/append";
var updateIncomeUrl=url+"/income/update";
var deleteIncomeUrl=url+"/income/delete";
//报表收入纯利润
var netIncomeReportUrl=url+"/income/netIncome";
//------------------------------------------

//------------------------------------------
//登陆
var userLogin=url+"/system/user/login";
//菜单列表
var listFunctionUrl=url+"/menu/listFunction";//有权限菜单
var listFunctionAllMenuUrl=url+"/menu/list";//无权限菜单，全部加载出来
var loginUrl=url+"/login/login";
var outUrl=url+"/login/out";
var registeredUserUrl=url+"/login/registeredUser";  //初始页面注册用户2018.12.23
//user
var userManagementListByPageUrl=url+"/user/listByPage";
var userManagementGetUrl=url+"/user/get";
var userManagementSaveUrl=url+"/user/append";
var userManagementListUpdateUrl=url+"/user/update";
var userManagementListDeleteUrl=url+"/user/delete";
var roleListUrl=url+"/role/list";
//角色权限role
var roleGetUrl=url+"/role/get";
var roleListByPageUrl=url+"/role/listByPage";
var roleAppendUrl=url+"/role/append";
var roleDeleteUrl=url+"/role/delete";
var roleUpdateUrl=url+"/role/update";

//分类
var consumeTypeListUrl=url+"/consumeType/list";