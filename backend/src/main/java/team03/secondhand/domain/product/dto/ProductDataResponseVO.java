package team03.secondhand.domain.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductDataResponseVO {
    private final String location;
    private final Long chatRoomCount;
    private final List<Long> watchListMemberIds;
    private final String productMainImgUrl;

    public ProductDataResponseVO(String location, Long chatRoomCount, List<Long> watchListMemberIds, String productMainImgUrl) {
        this.location = location;
        this.chatRoomCount = chatRoomCount;
        this.watchListMemberIds = watchListMemberIds;
        this.productMainImgUrl = productMainImgUrl;
    }
}
