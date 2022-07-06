package pl.mantiscrab.linkshortener;

public class LinkUpdateDto {
    private final String name;
    private final String password;

    public LinkUpdateDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
