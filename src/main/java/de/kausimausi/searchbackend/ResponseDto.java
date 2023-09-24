package de.kausimausi.searchbackend;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDto<T> {
    T data;
    int numPages;
}
