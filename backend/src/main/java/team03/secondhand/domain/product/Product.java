package team03.secondhand.domain.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.category.Category;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.productImg.ProductImg;
import team03.secondhand.domain.watchlist.Watchlist;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
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
    private final Integer lookupCount = 0;

    @Column(name = "sales_status")
    private final String salesStatus = "판매중";

    @Column(name = "create_at")
    private final LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

    @Column(name = "update_at")
    private final LocalDateTime updatedAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private final List<ProductImg> productImgList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private final List<Watchlist> watchlistArrayList = new ArrayList<>();


    @Builder
    public Product(String title, Integer price, String content, Category category, Location location, Member member) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.category = category;
        this.location = location;
        this.member = member;
    }

    public void addProductId(String imgUrl) {
        productImgList.add(new ProductImg(imgUrl, this));
    }

    public void clearProductId() {
        productImgList.clear();
    }
}

