package com.ktriasia.contractmanager.model.dto;

import com.ktriasia.contractmanager.model.enums.ClauseCategory;
import lombok.Data;

/**
 * 条款DTO类。
 * <p>用于封装条款信息的数据传输对象，包含条款ID、标题、内容和类别等信息。</p>
 * <ul>
 *   <li>clauseId：条款唯一标识</li>
 *   <li>title：条款标题</li>
 *   <li>content：条款内容</li>
 *   <li>category：条款类别</li>
 * </ul>
 * @author Ktriasia
 * @since 2025-9-19
 * @version 1.0.0
 */
@Data
public class ClauseDTO {
    private Integer clauseId;
    private String title;
    private String content;
    private String category;

    public static ClauseDTO fromEntity(com.ktriasia.contractmanager.model.pojo.Clause clause) {
        ClauseDTO dto = new ClauseDTO();
        dto.clauseId = clause.getClauseId();
        dto.title = clause.getTitle();
        dto.content = clause.getContent();
        if (clause.getClauseCategory() != null) {
            dto.category = clause.getClauseCategory().name();
        }
        return dto;
    }
}