package avisi.hackathon.dtos;

import lombok.Data;

@Data
public class KerntaaktDto {
    private int id;
    private String name;
    private WerkprocessDto[] werkprocessen;
}
