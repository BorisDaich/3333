package biz.daich.tambour.shlicht.service.mapper;

import biz.daich.tambour.shlicht.domain.Images;
import biz.daich.tambour.shlicht.service.dto.ImagesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Images} and its DTO {@link ImagesDTO}.
 */
@Mapper(componentModel = "spring")
public interface ImagesMapper extends EntityMapper<ImagesDTO, Images> {}
