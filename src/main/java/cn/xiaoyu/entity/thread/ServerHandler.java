package cn.xiaoyu.entity.thread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.xiaoyu.common.Base;
import cn.xiaoyu.service.tally.SummaryService;

@Component
public class ServerHandler  extends Base{
    @Autowired
    protected SummaryService summaryService;
    public static ServerHandler  serverHandler ;
    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {  
        serverHandler = this;  
        serverHandler.summaryService = this.summaryService;        
        // 初使化时将已静态化的testService实例化
    }  
//    //测试调用
//    public void test(){
//        serverHandler.summaryService.dailyConsumptionYear(2018);
//    }

}