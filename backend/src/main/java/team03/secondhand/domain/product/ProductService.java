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
import team03.secondhand.domain.product.dto.request.RequestProductCreateDTO;
import team03.secondhand.domain.product.dto.response.ResponseProductCreateDTO;
import team03.secondhand.domain.product.dto.response.ResponseProductHomeDTO;
import team03.secondhand.domain.productImg.ProductImg;
import team03.secondhand.domain.productImg.ProductImgRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public ResponseProductCreateDTO createProduct(RequestProductCreateDTO request) {
        // Get Category
        Category category = categoryRepository.getReferenceById(request.getCategoryId());

        // Get Location
        Location location = locationRepository.getReferenceById(request.getLocationId());

        // Get Member
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
        Optional<Product> byId = productRepository.findById(1L);

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
    public List<ResponseProductHomeDTO> getAllProductByFilter(Long locationId, Long categoryId) {
        List<Product> products = productRepository.findProductByFilter(locationId, categoryId);
        log.info(products.toString());
        return products.stream()
                .map(this::convertToHomeDTO)
                .collect(Collectors.toList());
    }

    private ResponseProductCreateDTO convertToProductDTO(Product product) {
        return ResponseProductCreateDTO.builder()
                .productId(product.getProductId())
                .build();
    }

    // TODO: 1. 관심목록 리턴용 DTO 작성 필요
    // TODO: 2. 채팅룸 기능 구현시 카운터 체크 기능 추가 바람
    private ResponseProductHomeDTO convertToHomeDTO(Product product) {
        String locationShortening = product.getLocation().getLocationShortening();
        String productImgUrl = productImgRepository.findFirstByProduct_ProductId(product.getProductId()).getImgUrl();
        return ResponseProductHomeDTO.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .updatedAt(product.getUpdatedAt())
                .price(product.getPrice())
                .location(locationShortening)
                .chatRoomCount(0L)
                .watchListCount(0L)
                .productImgUrl(productImgUrl)
                .build();
    }

}
