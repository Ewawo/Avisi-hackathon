package avisi.hackathon.dtos;

import lombok.Data;

@Data
public class CriteriumDto {
    private int id;
    private String name;
    private String onvoldoende;
    private String orientatieVoldoende;
    private String orientatieGoed;
    private String developerVoldoende;
    private String developerGoed;
    private String expertVoldoende;
    private String expertGoed;
    private String kerntaak;
    private String werkprocess;
}
