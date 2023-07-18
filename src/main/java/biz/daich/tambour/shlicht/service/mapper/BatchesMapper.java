package biz.daich.tambour.shlicht.service.mapper;

import biz.daich.tambour.shlicht.domain.Batches;
import biz.daich.tambour.shlicht.domain.Catalogs;
import biz.daich.tambour.shlicht.service.dto.BatchesDTO;
import biz.daich.tambour.shlicht.service.dto.CatalogsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Batches} and its DTO {@link BatchesDTO}.
 */
@Mapper(componentModel = "spring")
public interface BatchesMapper extends EntityMapper<BatchesDTO, Batches> {
    @Mapping(target = "colorCatalog", source = "colorCatalog", qualifiedByName = "catalogsId")
    BatchesDTO toDto(Batches s);

    @Named("catalogsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CatalogsDTO toDtoCatalogsId(Catalogs catalogs);
}
