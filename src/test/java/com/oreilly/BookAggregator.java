package com.oreilly;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.time.LocalDate;

public class BookAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor,
                                     ParameterContext context) throws ArgumentsAggregationException {
        return new Book(accessor.getString(0),
                        accessor.getString(1),
                        accessor.getString(2),
                        accessor.get(3, LocalDate.class));
    }
}
