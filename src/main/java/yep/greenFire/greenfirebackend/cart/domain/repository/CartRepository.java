package yep.greenFire.greenfirebackend.cart.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.cart.domain.entity.Cart;
import yep.greenFire.greenfirebackend.cart.dto.response.CartItemResponse;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(
            "select new yep.greenFire.greenfirebackend.cart.dto.response.CartItemResponse(c, o, p) " +
                    "from Cart c join ProductOption o on c.optionCode = o.optionCode " +
                    "join Product p on o.productCode = p.productCode " +
                    "where c.memberCode = :memberCode"
    )
    List<CartItemResponse> findByMemberCode(Long memberCode);


    Optional<Cart> findByCartCode(Long cartCode);


}
