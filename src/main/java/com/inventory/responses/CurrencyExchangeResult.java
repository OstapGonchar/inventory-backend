package com.inventory.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyExchangeResult {
    private String from;
    private String to;
    private Double amountToConvert;
    private Double convertedAmount;
}
