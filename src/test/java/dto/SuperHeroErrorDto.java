package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class SuperHeroErrorDto {
    String message;
    String code;
}
