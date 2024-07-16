package college.mysql.service;

import college.mysql.dao.MemberUserMapper;
import college.mysql.entity.MemberUserDO;
import college.mysql.handler.AfterHandler;
import college.mysql.handler.Handler2;
import college.mysql.handler.SampleAfter;
import college.mysql.handler.SampleAfter2;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: EDY
 * Date: 2024/5/11
 * Time: 18:01
 * Version:V1.0
 */
@Service
public class MySqlTestService {

    @Resource
    private MemberUserMapper memberUserMapper;

    @Transactional
    @AfterHandler({SampleAfter.class, SampleAfter2.class})
    public Integer transaction(String name) {
        List<MemberUserDO> memberUserDOs = memberUserMapper.selectList(Wrappers.<MemberUserDO>lambdaQuery().orderByDesc(MemberUserDO::getId).last("LIMIT 1"));
        return memberUserMapper.deleteById(memberUserDOs.get(0).getId());
    }

    @Transactional
    @AfterHandler(Handler2.class)
    public Integer transaction2() {
        transaction("1");
        return 2;
    }
}
