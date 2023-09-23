package com.exam.exam.management.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPaginationRequest {
    private Long id;
    private String name;
    private String title;
    private Integer per_page_record;
    private Integer page;

}
