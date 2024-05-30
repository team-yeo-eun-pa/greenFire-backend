package yep.greenFire.greenfirebackend.user.report.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.user.report.domain.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
