package pl.mantiscrab.linkshortener;

import org.springframework.data.repository.CrudRepository;

interface LinkRepository extends CrudRepository<Link, String> {
    boolean existsById(String redirectId);
}
