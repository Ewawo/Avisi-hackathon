package avisi.hackathon.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String token;
    private String role;
}
