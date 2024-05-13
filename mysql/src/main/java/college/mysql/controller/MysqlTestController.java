package college.mysql.controller;

import college.mysql.dao.MemberUserMapper;
import college.mysql.entity.MemberUserDO;
import college.mysql.service.MySqlTestService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: EDY
 * Date: 2024/5/11
 * Time: 10:43
 * Version:V1.0
 */
@RequestMapping("mysql")
@RestController
@Slf4j
public class MysqlTestController {

    @Resource
    private MemberUserMapper memberUserMapper;

    @Autowired
    private MySqlTestService mySqlTestService;

    @GetMapping("memberList")
    public List<MemberUserDO> getMemberList() {
        log.info("hello mysql 神主日");
        return memberUserMapper.selectList(Wrappers.<MemberUserDO>lambdaQuery().last("LIMIT 10"));
    }

    @GetMapping("transaction")
    public Integer transaction() {
        return mySqlTestService.transaction();
    }

    @GetMapping("transaction2")
    public Integer transaction2() {
        return mySqlTestService.transaction2();
    }
}
