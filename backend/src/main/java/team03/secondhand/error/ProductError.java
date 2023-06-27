package team03.secondhand.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductError {

    @Getter
    @Setter
    public static class NotFoundProduct extends RuntimeException {}
}
