package pl.mantiscrab.linkshortener;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Link {
    @Id
    private String id;
    private String name;
    private String targetUrl;
    private int visits;
    private String password;

    public Link() {
    }

    public Link(String id, String name, String targetUrl) {
        this.id = id;
        this.name = name;
        this.targetUrl = targetUrl;
    }

    public Link(String id, String name, String targetUrl, String password) {
        this.id = id;
        this.name = name;
        this.targetUrl = targetUrl;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }


    public int getVisits() {
        return visits;
    }

    public String getPassword() {
        return password;
    }
    public void incrementVisits() {
        this.visits++;
    }
}
