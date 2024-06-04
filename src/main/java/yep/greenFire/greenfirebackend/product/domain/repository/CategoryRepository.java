package yep.greenFire.greenfirebackend.product.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
