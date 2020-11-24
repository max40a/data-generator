package com.data.generator.service;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class NumberService {

    public List<Long> generateLongs(@Nonnull Integer limit) {
        Preconditions.checkArgument(limit >= 0, format("limit must be >= 0; current value = %d", limit));

        return new Random()
            .longs(limit)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .collect(toList());
    }
}
