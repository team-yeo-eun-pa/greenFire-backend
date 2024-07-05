package yep.greenFire.greenfirebackend.product.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
