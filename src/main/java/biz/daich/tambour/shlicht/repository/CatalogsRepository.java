package biz.daich.tambour.shlicht.repository;

import biz.daich.tambour.shlicht.domain.Catalogs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Catalogs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatalogsRepository extends JpaRepository<Catalogs, String>, JpaSpecificationExecutor<Catalogs> {}
