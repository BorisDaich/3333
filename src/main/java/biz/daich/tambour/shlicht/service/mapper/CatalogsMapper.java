package biz.daich.tambour.shlicht.service.mapper;

import biz.daich.tambour.shlicht.domain.Catalogs;
import biz.daich.tambour.shlicht.service.dto.CatalogsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Catalogs} and its DTO {@link CatalogsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CatalogsMapper extends EntityMapper<CatalogsDTO, Catalogs> {}
