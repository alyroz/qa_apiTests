package dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SuperHeroResponseDto {
    String birthDate;
    String city;
    String fullName;
    String gender;
    int id;
    String mainSkill;
    String phone;

}
