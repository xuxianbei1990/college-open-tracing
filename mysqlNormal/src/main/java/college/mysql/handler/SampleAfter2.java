package college.mysql.handler;

import org.springframework.stereotype.Service;

/**
 * User: EDY
 * Date: 2024/5/13
 * Time: 15:07
 * Version:V1.0
 */
@Service
public class SampleAfter2 implements Handler2 {


    @Override
    public void execute(Object o) {
        System.out.println("SampleAfter execute2");
        throw new RuntimeException("SampleAfter execute2 error");
    }
}
