package com.gfg.jdblblr2librarymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Builder
@AllArgsConstructor
@Data
public class _Book {
    @Id
    String title;
    private String author;
    private Integer qty;
}
