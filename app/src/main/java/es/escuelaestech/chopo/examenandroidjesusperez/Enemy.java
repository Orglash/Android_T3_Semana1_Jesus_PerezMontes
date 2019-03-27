package es.escuelaestech.chopo.examenandroidjesusperez;

import java.util.UUID;

public class Enemy {

    private String eId;
    private String eName;
    private String eAge;
    private String eDeed;

    public Enemy(String name, String age, String deed) {
        eId = UUID.randomUUID().toString();
        eName = name;
        eAge = age;
        eDeed = deed;
    }

    public String getId() {
        return eId;
    }

    public void setId(String id) {
        this.eId = id;
    }

    public String getName() {
        return eName;
    }

    public void setName(String name) {
        this.eName = name;
    }

    public String getAge() {
        return eAge;
    }

    public void setAge(String age) {
        this.eAge = age;
    }

    public String getDeed() {
        return eDeed;
    }

    public void setDeed(String deed) {
        this.eDeed = deed;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "ID='" + eId + '\'' +
                ", Compañía='" + eDeed + '\'' +
                ", Nombre='" + eName + '\'' +
                ", Cargo='" + eAge + '\'' +
                '}';
    }


}

