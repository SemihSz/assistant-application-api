package com.assistant.commonapi.task;

import java.util.function.BiFunction;

public interface Mappers<T, R, S> extends BiFunction<T, R, S> {
}
