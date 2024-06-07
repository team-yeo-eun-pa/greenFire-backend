package yep.greenFire.greenfirebackend.report.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@RequiredArgsConstructor
@Setter
@ToString
public class ReportCreateRequest {

    private String reportReason;

    private LocalDate reportDate;

    private String reportType;

    private Long commentCode;

    private Long reviewCode;

    private Long storeCode;

    private Long memberCode;


}
