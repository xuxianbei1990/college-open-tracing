package college.alerting.entity;

import lombok.Data;

import java.util.List;

@Data
public class AlarmMessage {

    // All scopes are defined in org.apache.skywalking.oap.server.core.source.DefaultScopeDefine.
    private int scopeId;

    private String scope;
    //Target scope entity name. Please follow the entity name definitions.
    private String name;
    //The ID of the scope entity that matches with the name. When using the relation scope, it is the source entity ID.
    private String id0;
    //When using the relation scope, it is the destination entity ID. Otherwise, it is empty.
    private String id1;

    //The rule name configured in alarm-settings.yml.
    private String ruleName;
    private String alarmMessage;
    private long startTime;
    private List<Tag> tags;


}
