package geoalert.com.co.geoalert.model;

public class Warning {

    private String name;
    private String description;
    private String ubication;

    public Warning(){super();}

    public Warning(String name,String description){
        this.name=name;
        this.description=description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

}
