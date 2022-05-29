package pl.mantiscrab.testdata;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface NameRepository extends CrudRepository<Name, Long> {

    @Query(value = "SELECT * from NAME ORDER BY number DESC LIMIT 10", nativeQuery = true)
    List<Name> findTopTenNames();
}
