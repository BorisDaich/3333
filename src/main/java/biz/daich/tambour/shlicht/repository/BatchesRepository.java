package biz.daich.tambour.shlicht.repository;

import biz.daich.tambour.shlicht.domain.Batches;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Batches entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatchesRepository extends JpaRepository<Batches, String>, JpaSpecificationExecutor<Batches> {}
