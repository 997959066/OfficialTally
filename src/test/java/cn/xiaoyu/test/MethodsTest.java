package cn.xiaoyu.test;

import java.util.List;

import cn.xiaoyu.common.Base;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xiaoyu.dao.tally.IncomeMapper;
import cn.xiaoyu.entity.tally.Income;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MethodsTest extends Base {

	@Test
	public void incomeDetails() {
		Income i=new Income();
		i.setYear(2018);
		List<Income> list = incomeMapper.incomeDetails(i);
		list.forEach(income -> {
			System.out.println(income.getSource() + " + " + income.getYear() + "  +  " + income.getMonth() + "  +  "
					+ income.getMoney());
		});

	}

}
