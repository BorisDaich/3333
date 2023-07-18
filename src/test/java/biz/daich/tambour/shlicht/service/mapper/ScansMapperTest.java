package biz.daich.tambour.shlicht.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScansMapperTest {

    private ScansMapper scansMapper;

    @BeforeEach
    public void setUp() {
        scansMapper = new ScansMapperImpl();
    }
}
