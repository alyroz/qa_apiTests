package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SuperHeroRequestDto {
    String birthDate;
    String city;
    String fullName;
    String gender;
    String mainSkill;
    String phone;

}
