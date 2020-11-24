package com.data.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.reverseOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberServiceTest {
    private NumberService numberService;

    @BeforeEach
    void setUp() {
        numberService = new NumberService();
    }

    @Test
    void generateLongs_should_return_empty_list_if_limit_parameter_is_zero() {
        List<Long> actualList = numberService.generateLongs(0);

        assertTrue(actualList.isEmpty());
    }

    @Test
    void generateLongs_should_fail_if_limit_less_than_zero() {
        assertThrows(IllegalArgumentException.class, () -> numberService.generateLongs(-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    void generateLongs_should_generate_required_data(int size) {
        List<Long> actualList = numberService.generateLongs(size);

        assertThat(actualList)
            .isNotEmpty()
            .hasSize(size)
            .hasOnlyElementsOfType(Long.class)
            .isSortedAccordingTo(Comparator.comparing(Long::longValue, reverseOrder()));
    }
}
