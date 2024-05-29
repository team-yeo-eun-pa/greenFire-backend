package yep.greenFire.greenfirebackend.admin.report.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.admin.report.domain.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
