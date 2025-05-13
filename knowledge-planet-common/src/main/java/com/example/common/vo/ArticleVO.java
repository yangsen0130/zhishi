// src/main/java/com/example/common/vo/ArticleVO.java
package com.example.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 可选: 如果字段为null则不在JSON中显示
public class ArticleVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title;
    private String content;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long authorId;
    private String authorName;
    private BigDecimal price;
    private LocalDateTime createTime;

    // 可选: 明确的权限标志
    private Boolean hasFullAccess; // true 表示有完整权限，false 表示没有（例如需要购买）
}