package org.mosaic.slack.libs.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.slack.libs.exception.ExceptionStatus;
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

    public static ResponseEntity<CommonResponse<String>> failed(ExceptionStatus e) {
        return ResponseEntity.status(e.getStatus())
            .body(CommonResponse.<String>builder()
                .success(false)
                .response(e.getMessage())
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