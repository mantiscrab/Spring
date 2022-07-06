package pl.mantiscrab.linkshortener;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mantiscrab.linkshortener.exception.WrongPasswordException;

import java.net.URI;
import java.util.Optional;

@RestController
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/api/links")
    ResponseEntity<LinkDto> createLink(@RequestBody LinkCreateDto linkCreateDto) {
        LinkDto createdLinkDto = linkService.createLink(linkCreateDto);
        return ResponseEntity
                .created(URI.create(createdLinkDto.getRedirectUrl()))
                .body(createdLinkDto);
    }

    @GetMapping("/api/links/{id}")
    ResponseEntity<LinkDto> getLinkInfo(@PathVariable String id) {
        Optional<LinkDto> linkDto = linkService.getLink(id);
        return linkDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/api/links/{id}")
    ResponseEntity<Void> updateLink(@RequestBody LinkUpdateDto linkUpdateDto, @PathVariable String id) {
        boolean linkUpdated = false;
        try {
            linkUpdated = linkService.updateLink(id, linkUpdateDto);
        } catch (WrongPasswordException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .header("reason", "wrong password")
                    .build();
        }
        if (linkUpdated) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("api/links/{id}")
    ResponseEntity<Void> deleteLink(@RequestHeader(required = false) String passwd, @PathVariable String id) {
        try {
            linkService.deleteLink(id, passwd);
        } catch (WrongPasswordException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/redir/{id}")
    ResponseEntity<String> redirectToLink(@PathVariable String id) {
        Optional<LinkDto> linkDto = linkService.visitLink(id);
        return linkDto
                .map(l -> ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", l.getTargetUrl())
                        .body(l.getTargetUrl()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
