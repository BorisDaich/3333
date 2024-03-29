package biz.daich.tambour.shlicht.repository;

import biz.daich.tambour.shlicht.domain.Images;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Images entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImagesRepository extends JpaRepository<Images, String>, JpaSpecificationExecutor<Images> {}
