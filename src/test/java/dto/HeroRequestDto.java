package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HeroRequestDto {
    String birthDate;
    String city;
    String fullName;
    String gender;
    String mainSkill;
    String phone;
}
