package com.toyRobot.util;

import java.util.Optional;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.toyRobot.model.Direction;

/**
 * This class convert source parameter into Enum.
 *
 */
public class StringToEnumConverter implements Converter<String, Direction> {
	@Override
	public Direction convert(String source) {
		try {
			if (StringUtils.isEmpty(source)) {
				throw new ConversionFailedException(null, null, null, null);
			}

			String direction = Optional.ofNullable(source).map(src -> src.toUpperCase())
					.orElseThrow(IllegalArgumentException::new);
			
			return Direction.valueOf(direction);
		} catch (IllegalArgumentException e) {
			throw new ConversionFailedException(null, null, source, e);
		}
	}
}
