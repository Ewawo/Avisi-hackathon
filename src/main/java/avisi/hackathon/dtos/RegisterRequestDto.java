package avisi.hackathon.dtos;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String firstname;
    private String surname;
    private String email;
    private String password;
    private boolean isTeacher;
}