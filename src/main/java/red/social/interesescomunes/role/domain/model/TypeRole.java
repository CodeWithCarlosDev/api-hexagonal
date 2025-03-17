package red.social.interesescomunes.role.domain.model;

public enum TypeRole {
    Miembro("Miembro"),
    Propietario("Propietario"),
    Administrador("Administrador");

    private String name;

    TypeRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
