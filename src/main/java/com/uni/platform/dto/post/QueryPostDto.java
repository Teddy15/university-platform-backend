package com.uni.platform.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryPostDto{
    private List<PostDto> posts;

    private long currentPage;
    private long itemsPerPage;
    private long totalPages;
    private long totalElements;
}
