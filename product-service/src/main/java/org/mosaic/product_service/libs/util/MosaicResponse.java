package org.mosaic.product_service.libs.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MosaicResponse<T> {
	private boolean success;
	private T response;
}
