package org.englising.com.englisingbe.multiplay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiPlayRequestDto {
    public Long multiplayId;
    public String genre;
}