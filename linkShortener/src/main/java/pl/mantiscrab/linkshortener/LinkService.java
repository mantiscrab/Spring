package pl.mantiscrab.linkshortener;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mantiscrab.linkshortener.exception.WrongPasswordException;

import java.util.Optional;

@Service
public class LinkService {
    public static final int REDIRECT_ID_LENGTH = 10;
    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Transactional
    public LinkDto createLink(LinkCreateDto linkCreateDto) {
        Link createdLink = LinkMapper.map(linkCreateDto, createId());
        Link savedLink = linkRepository.save(createdLink);
        LinkDto savedLinkDto = LinkMapper.map(savedLink);
        return savedLinkDto;
    }

    public Optional<LinkDto> getLink(String id) {
        return linkRepository.findById(id).map(LinkMapper::map);
    }

    private String createId() {
        String randomAlphanumeric = null;
        do {
            randomAlphanumeric = RandomStringUtils.randomAlphanumeric(REDIRECT_ID_LENGTH);
        } while (linkRepository.existsById(randomAlphanumeric));
        return randomAlphanumeric;
    }

    @Transactional
    public Optional<LinkDto> visitLink(String id) {
        Optional<Link> link = linkRepository.findById(id);
        link.ifPresent(Link::incrementVisits);
        return link.map(LinkMapper::map);
    }

    @Transactional
    public boolean updateLink(String id, LinkUpdateDto linkUpdateDto) {
        Optional<Link> optionalLink = linkRepository.findById(id);

        if (optionalLink.isEmpty()) return false;

        Link link = optionalLink.get();

        if (link.getPassword() == null) throw new WrongPasswordException();
        if (linkUpdateDto.getPassword() == null) throw new WrongPasswordException();
        if (!linkUpdateDto.getPassword().equals(link.getPassword())) throw new WrongPasswordException();

        link.setName(linkUpdateDto.getName());
        return true;
    }

    @Transactional
    public void deleteLink(String id, String passwd) {
        Optional<Link> optionalLink = linkRepository.findById(id);

        if (optionalLink.isEmpty()) return;
        Link link = optionalLink.get();
        if (link.getPassword() == null) throw new WrongPasswordException();
        if (passwd == null) throw new WrongPasswordException();
        if (!link.getPassword().equals(passwd)) throw new WrongPasswordException();

        linkRepository.delete(link);
    }
}














