package college.alerting.controller;

import college.alerting.entity.AlarmMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alerting")
@Slf4j
public class AlertingController {

    private List<AlarmMessage> alarmMessages = new ArrayList<>();

    @PostMapping("save-alert")
    public String saveAlert(@RequestBody List<AlarmMessage> alarmMessages) {
        log.info(alarmMessages.toString());
        alarmMessages.addAll(alarmMessages);
        return "Alert saved successfully";
    }


    @GetMapping("get-alerts")
    public List<AlarmMessage> getAlerts() {
        return alarmMessages;
    }
}
