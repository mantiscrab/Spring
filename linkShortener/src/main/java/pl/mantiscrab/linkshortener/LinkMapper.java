package pl.mantiscrab.linkshortener;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class LinkMapper {
    public static LinkDto map(Link link) {
        return new LinkDto(
                link.getId(),
                link.getName(),
                link.getTargetUrl(),
                createRedirectUrl(link.getId()),
                link.getVisits()
        );
    }

    private static String createRedirectUrl(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/redir/{id}")
                .buildAndExpand(id)
                .toUriString();
    }

    public static Link map(LinkCreateDto linkCreateDto, String id) {
        return new Link(
                id,
                linkCreateDto.getName(),
                linkCreateDto.getTargetUrl(),
                linkCreateDto.getPassword());
    }
}
