package college.mysql.service;

import college.mysql.dao.MemberUserMapper;
import college.mysql.entity.MemberUserDO;
import college.mysql.handler.AfterHandler;
import college.mysql.handler.Handler2;
import college.mysql.handler.SampleAfter;
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
    @AfterHandler(SampleAfter.class)
    public Integer transaction() {
        List<MemberUserDO> memberUserDOs = memberUserMapper.selectList(Wrappers.<MemberUserDO>lambdaQuery().orderByDesc(MemberUserDO::getId).last("LIMIT 1"));
        return memberUserMapper.deleteById(memberUserDOs.get(0).getId());
    }

    @Transactional
    @AfterHandler(Handler2.class)
    public Integer transaction2() {
        transaction();
        return 2;
    }
}
