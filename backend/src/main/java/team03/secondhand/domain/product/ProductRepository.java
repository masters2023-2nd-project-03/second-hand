package team03.secondhand.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByProductId(Long productId);

    @Query("SELECT p FROM Product p " +
            "WHERE p.location.locationId = :locationId " +
            "AND (:categoryId IS NULL OR p.category.categoryId = :categoryId) " +
            "ORDER BY p.updatedAt DESC")
    List<Product> findProductByFilter(@Param("locationId") Long locationId, @Param("categoryId") Long categoryId);

    List<Product> findAllByOrderByUpdatedAtDesc();

}
