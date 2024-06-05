package yep.greenFire.greenfirebackend.report.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

public class ReportVO {

    private Long reportCode;
    private String reportType;

    private String reportReason;

    private LocalDateTime reportDate;

    public ReportVO(Long reportCode, String reportType, String reportReason, LocalDateTime reportDate) {
        this.reportCode = reportCode;
        this.reportType = reportType;
        this.reportReason = reportReason;
        this.reportDate = reportDate;
    }
}
