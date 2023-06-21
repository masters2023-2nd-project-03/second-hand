package team03.secondhand.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team03.secondhand.domain.category.Category;
import team03.secondhand.domain.category.CategoryRepository;
import team03.secondhand.domain.location.Location;
import team03.secondhand.domain.location.LocationRepository;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.MemberRepository;
import team03.secondhand.domain.product.dto.ProductDataRequestDTO;
import team03.secondhand.domain.product.dto.ProductDataResponseDTO;
import team03.secondhand.domain.product.dto.ProductDataResponseVO;
import team03.secondhand.domain.productImg.ProductImg;
import team03.secondhand.domain.productImg.ProductImgRepository;
import team03.secondhand.domain.watchlist.WatchlistRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;
    private final ProductImgRepository productImgRepository;
    private final WatchlistRepository watchlistRepository;

    @Transactional
    public ProductDataResponseDTO.SimpleInfo createProduct(ProductDataRequestDTO request) {
        // Get Info
        Category category = categoryRepository.getReferenceById(request.getCategoryId());
        Location location = locationRepository.getReferenceById(request.getLocationId());
        Member member = memberRepository.getReferenceById(request.getMemberId());

        // Create Product
        Product product = Product.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .content(request.getContent())
                .category(category)
                .location(location)
                .member(member)
                .build();
        Product savedProduct = productRepository.save(product);

        // Create Product Images
        for (String imageUrl : request.getProductImageUrls()) {
            ProductImg productImg = ProductImg.builder()
                    .imgUrl(imageUrl)
                    .product(product)
                    .build();
            productImgRepository.save(productImg);
        }

        return convertToProductDTO(savedProduct);
    }

    @Transactional
    public List<ProductDataResponseDTO.HomeInfo> getAllProductByFilter(Long memberId, Long locationId, Long categoryId) {
        List<Product> products;
        if (locationId == null) {
            products = productRepository.findAllByOrderByUpdatedAtDesc();
        } else {
            products = productRepository.findProductByFilter(locationId, categoryId);
        }

        return products.stream()
                .map(product -> convertToHomeDTO(product, memberId))
                .collect(Collectors.toList());
    }

    private ProductDataResponseDTO.SimpleInfo convertToProductDTO(Product product) {
        return new ProductDataResponseDTO.SimpleInfo(product);
    }


    private ProductDataResponseDTO.HomeInfo convertToHomeDTO(Product product, Long memberId) {
        String locationShortening = product.getLocation().getLocationShortening();
        // TODO: 1. 채팅룸 기능 구현시 카운터 체크 기능 추가 바람
        int watchlistCount = watchlistRepository.countByProduct_ProductId(product.getProductId());
        boolean isWatchlistChecked = watchlistRepository.existsByProductProductIdAndMember_MemberId(product.getProductId(), memberId);
        String productImgUrl = productImgRepository.findFirstByProduct_ProductId(product.getProductId()).getImgUrl();

        ProductDataResponseVO responseVO = ProductDataResponseVO.builder()
                .location(locationShortening)
                .chatRoomCount(0L)
                .watchlistCount(watchlistCount)
                .isWatchlistChecked(isWatchlistChecked)
                .productMainImgUrl(productImgUrl)
                .build();

        return new ProductDataResponseDTO.HomeInfo(product, responseVO);
    }

}
