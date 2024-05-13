package college.mysql.handler.after;

import org.springframework.stereotype.Service;

/**
 * User: EDY
 * Date: 2024/5/13
 * Time: 15:07
 * Version:V1.0
 */
@Service
public class SampleAfter implements Handler2 {

    @Override
    public void execute() {
        System.out.println("SampleAfter execute");
    }
}
