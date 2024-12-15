package org.mosaic.order_service.libs.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseUtils {

	public static <T> ResponseEntity<MosaicResponse<T>> ok(T response) {
		return ResponseEntity.ok(MosaicResponse.<T>builder()
			.success(true)
			.response(response)
			.build());
	}

	public static <T> ResponseEntity<MosaicResponse<T>> created(T response) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(MosaicResponse.<T>builder()
				.success(true)
				.response(response)
				.build());
	}

	public static ResponseEntity<MosaicResponse<String>> noContent() {
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
			.body(MosaicResponse.<String>builder()
				.success(true)
				.build());
	}

	public static ResponseEntity<MosaicResponse<String>>
					failed(HttpStatus status, Exception e) {
		return ResponseEntity.status(status)
			.body(MosaicResponse.<String>builder()
				.success(false)
				.response(e.getMessage())
				.build());
	}


}
