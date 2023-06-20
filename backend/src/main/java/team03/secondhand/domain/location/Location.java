package team03.secondhand.domain.location;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.secondhand.domain.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "location")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "location_details")
    private String locationDetails;

    @Column(name = "location_shortening")
    private String locationShortening;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private List<Product> products = new ArrayList<>();

    // Getter and Setter methods
}
