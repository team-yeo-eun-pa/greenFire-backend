package yep.greenFire.greenfirebackend.admin.notice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
