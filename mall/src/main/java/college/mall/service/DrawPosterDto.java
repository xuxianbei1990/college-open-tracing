package college.mall.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DrawPosterDto {

    private String merchantName;

    private String merchantContext;

    private BigDecimal amount;
}
