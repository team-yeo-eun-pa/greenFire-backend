package yep.greenFire.greenfirebackend.report.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@Setter
public class ReportCreateRequest {

    private String reportReason;

    private Date reportDate;

    private String reportType;

    private Long commentCode;

    private Long reviewCode;

    private Long storeCode;

    private Long memberCode;


}
