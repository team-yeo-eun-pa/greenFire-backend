package yep.greenFire.greenfirebackend.admin.category.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.admin.category.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}