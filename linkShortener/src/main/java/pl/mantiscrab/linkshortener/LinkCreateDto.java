package pl.mantiscrab.linkshortener;

public class LinkCreateDto {
    private final String name;
    private final String password;
    private final String targetUrl;

    public LinkCreateDto(String name, String password, String targetUrl) {
        this.name = name;
        this.password = password;
        this.targetUrl = targetUrl;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getTargetUrl() {
        return targetUrl;
    }
}
