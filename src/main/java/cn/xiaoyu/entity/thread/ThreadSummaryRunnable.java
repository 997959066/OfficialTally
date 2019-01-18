package cn.xiaoyu.entity.thread;

import java.util.Calendar;

public class ThreadSummaryRunnable extends ServerHandler  implements Runnable{

	
	
	
	public ThreadSummaryRunnable() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
        try
        {
                Thread.sleep(1000);//挂起1秒
                Calendar date = Calendar.getInstance();
	            int year =  date.get(Calendar.YEAR);
	            serverHandler.summaryService.dailyConsumptionYear(year,getUser().getId());
	            System.out.println(year+"线程");
                
        }
        catch (InterruptedException e)
        {
                return;
        }
	}

}
