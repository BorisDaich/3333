package biz.daich.tambour.shlicht.service.mapper;

import biz.daich.tambour.shlicht.domain.Batches;
import biz.daich.tambour.shlicht.domain.Images;
import biz.daich.tambour.shlicht.domain.Scans;
import biz.daich.tambour.shlicht.service.dto.BatchesDTO;
import biz.daich.tambour.shlicht.service.dto.ImagesDTO;
import biz.daich.tambour.shlicht.service.dto.ScansDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Scans} and its DTO {@link ScansDTO}.
 */
@Mapper(componentModel = "spring")
public interface ScansMapper extends EntityMapper<ScansDTO, Scans> {
    @Mapping(target = "image", source = "image", qualifiedByName = "imagesId")
    @Mapping(target = "productionBatch", source = "productionBatch", qualifiedByName = "batchesId")
    ScansDTO toDto(Scans s);

    @Named("imagesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ImagesDTO toDtoImagesId(Images images);

    @Named("batchesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BatchesDTO toDtoBatchesId(Batches batches);
}
