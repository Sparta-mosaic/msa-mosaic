package org.mosaic.auth.libs.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseUtils {

    public static <T> ResponseEntity<CommonResponse<T>> ok(T response) {
        return ResponseEntity.ok(CommonResponse.<T>builder()
            .success(true)
            .response(response)
            .build());
    }

    public static <T> ResponseEntity<CommonResponse<T>> created(T response) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(CommonResponse.<T>builder()
                .success(true)
                .response(response)
                .build());
    }

    public static ResponseEntity<CommonResponse<Void>> noContent() {
        ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(CommonResponse.<Void>builder()
                .success(true)
                .build());
    }

    public static ResponseEntity<CommonResponse<String>> failed(int statusCode, String message) {
        return ResponseEntity.status(statusCode)
            .body(CommonResponse.<String>builder()
                .success(false)
                .response(message)
                .build());
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CommonResponse<T> {
        private boolean success;
        private T response;
    }
}
