package team03.secondhand.domain.product;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import team03.secondhand.domain.product.vo.ProductSearchCondition;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static team03.secondhand.domain.category.QCategory.*;
import static team03.secondhand.domain.location.QLocation.*;
import static team03.secondhand.domain.member.QMember.member;
import static team03.secondhand.domain.product.QProduct.product;

@Repository
public class ProductQueryRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public ProductQueryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public Optional<Product> getDetailProductBy(Long productId) {
        return Optional.ofNullable(queryFactory
                .select(product)
                .from(product)
                .join(product.member, member).fetchJoin()
                .join(product.location, location).fetchJoin()
                .join(product.category, category).fetchJoin()
                .where(product.productId.eq(productId))
                .fetchOne());
    }

    public List<Product> getProductsBy(ProductSearchCondition condition, Pageable pageable) {
        return queryFactory
                .select(product)
                .from(product)
                .join(product.category, category).fetchJoin()
                .join(product.location, location).fetchJoin()
                .where(locationIdEqual(condition.getLocationId())
                        ,categoryIdEqual(condition.getCategoryId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.productId.desc())
                .fetch();
    }

    private BooleanExpression locationIdEqual(Long locationId) {
        return locationId != null ? product.location.locationId.eq(locationId) : null;
    }

    private BooleanExpression categoryIdEqual(Long categoryId) {
        return categoryId != null ? product.category.categoryId.eq(categoryId) : null;
    }


}
