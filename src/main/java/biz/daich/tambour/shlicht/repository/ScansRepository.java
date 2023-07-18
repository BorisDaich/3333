package biz.daich.tambour.shlicht.repository;

import biz.daich.tambour.shlicht.domain.Scans;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Scans entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScansRepository extends JpaRepository<Scans, String>, JpaSpecificationExecutor<Scans> {}
