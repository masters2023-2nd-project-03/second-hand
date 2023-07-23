package team03.secondhand.domain.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.category.Category;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.product.model.ProductWatchList;
import team03.secondhand.domain.product.model.ProductImages;
import team03.secondhand.model.Timestamped;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "lookup_count")
    private Integer lookupCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_status")
    private final ProductState salesStatus = ProductState.판매중;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private ProductImages productImages;

    @Embedded
    private ProductWatchList productWatchList;

    @Builder
    public Product(String title, Integer price, String content, Category category, Location location, Member member) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.category = category;
        this.location = location;
        this.member = member;
        this.lookupCount = 0;
    }

    public String getCategoryTitle() {
        return this.category.getTitle();
    }

    public String getLocationShortening() {
        return this.location.getLocationShortening();
    }

    public int getWatchCount() {
        return productWatchList.getWatchCount();
    }

    public boolean isWatchedByMemberId(Long memberId) {
        return productWatchList.isWatchedByMemberId(memberId);
    }

    public String getSellerNickname() {
        return member.getNickname();
    }

    public List<String> getImages() {
        return productImages.getImageUrlList();
    }

    public void changeImages(List<String> images) {
        this.productImages = new ProductImages(images);
    }

    public void incrementLookupCount() {
        lookupCount++;
    }

}

