package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class HeroErrorDto {
    int status;
    String error;
}
