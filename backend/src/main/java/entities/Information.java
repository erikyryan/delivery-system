package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public abstract class Information {

    @Id
    private long Id;

    private String Date;
    private String Name;
    private String SocialsSecurity;
    private String Cellphone;
}
