package com.timeSync.www.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author lwf
 * &#064;date 2023/9/8 9:10
 */
@Data
@ApiModel
public class SearchMyMeetingListByPageFrom {
    @NotNull
    @Min(1)
    private Integer page;

    @NotNull
    @Range(min = 1,max = 20)
    private Integer length;
}
