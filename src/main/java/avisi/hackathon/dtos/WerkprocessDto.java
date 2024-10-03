package avisi.hackathon.dtos;

import lombok.Data;

@Data
public class WerkprocessDto {
    private int id;
    private String name;
    private String description;
    private CriteriumCollectionDto criteriumCollectionDto;
}
