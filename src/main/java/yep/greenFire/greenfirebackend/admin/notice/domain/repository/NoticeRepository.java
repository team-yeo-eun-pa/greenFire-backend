package yep.greenFire.greenfirebackend.admin.notice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;
import yep.greenFire.greenfirebackend.admin.notice.domain.type.NoticeStatusType;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Page<Notice> findByNoticeStatusNot(Pageable pageable, NoticeStatusType noticeStatusType);

    @EntityGraph(attributePaths = {"member"})
    Optional<Notice> findByNoticeCodeAndStatusNot (Integer NoticeCode, NoticeStatusType noticeStatusType);
}
