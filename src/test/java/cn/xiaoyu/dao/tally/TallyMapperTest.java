package cn.xiaoyu.dao.tally;

import cn.xiaoyu.Startup;
import cn.xiaoyu.common.Base;
import cn.xiaoyu.entity.tally.Tally;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: JMSH9440
 * @Date: 2019/8/3 15:19
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class TallyMapperTest extends Base {

    @Test
    public void listByYear() {
       List<Tally> tallyList=tallyMapper.listByYear(2019,1);
        System.out.println(tallyList);
        Assert.assertNotNull(tallyList);
    }
}