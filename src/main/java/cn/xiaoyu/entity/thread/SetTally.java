package cn.xiaoyu.entity.thread;

import lombok.Setter;

public class SetTally extends ServerHandler implements Runnable {

	private @Setter Integer userId;

	public void run(){
			logger.debug("run ...........");
			serverHandler.summaryService.dailyConsumptionYear(null, userId);;
	}
	public SetTally() {
		super();
	}
}
