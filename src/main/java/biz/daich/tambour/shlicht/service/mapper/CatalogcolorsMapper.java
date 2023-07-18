package biz.daich.tambour.shlicht.service.mapper;

import biz.daich.tambour.shlicht.domain.Catalogcolors;
import biz.daich.tambour.shlicht.domain.Catalogs;
import biz.daich.tambour.shlicht.service.dto.CatalogcolorsDTO;
import biz.daich.tambour.shlicht.service.dto.CatalogsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Catalogcolors} and its DTO {@link CatalogcolorsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CatalogcolorsMapper extends EntityMapper<CatalogcolorsDTO, Catalogcolors> {
    @Mapping(target = "catalog", source = "catalog", qualifiedByName = "catalogsId")
    CatalogcolorsDTO toDto(Catalogcolors s);

    @Named("catalogsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CatalogsDTO toDtoCatalogsId(Catalogs catalogs);
}
