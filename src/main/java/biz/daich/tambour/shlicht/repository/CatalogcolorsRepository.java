package biz.daich.tambour.shlicht.repository;

import biz.daich.tambour.shlicht.domain.Catalogcolors;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Catalogcolors entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatalogcolorsRepository extends JpaRepository<Catalogcolors, String>, JpaSpecificationExecutor<Catalogcolors> {}
