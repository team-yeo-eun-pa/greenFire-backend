package yep.greenFire.greenfirebackend.admin.notice.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NoticeStatusType {

    ACTIVE,
    DELETE
    ;

//    private final String value;
//
//    NoticeStatusType(String value) {
//        this.value = value;
//    }
//
//
//    @JsonCreator
//    public NoticeStatusType from(String value) {
//        for(NoticeStatusType status : NoticeStatusType.values()) {
//            if(status.getValue().equals(value)) {
//                return status;
//            }
//        }
//        return null;
//    }
//
//    @JsonValue
//    public String getValue() {return value;}

}


