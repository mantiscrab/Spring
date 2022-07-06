package pl.mantiscrab.linkshortener;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class LinkDto {
    private String id;
    private String name;
    private String targetUrl;
    private String redirectUrl;
    private Integer visits;

    public LinkDto(String id, String name, String targetUrl, String redirectUrl, Integer visits) {
        this.id = id;
        this.name = name;
        this.targetUrl = targetUrl;
        this.redirectUrl = redirectUrl;
        this.visits = visits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

}
