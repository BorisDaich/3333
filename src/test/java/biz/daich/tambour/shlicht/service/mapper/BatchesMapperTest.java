package biz.daich.tambour.shlicht.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BatchesMapperTest {

    private BatchesMapper batchesMapper;

    @BeforeEach
    public void setUp() {
        batchesMapper = new BatchesMapperImpl();
    }
}
