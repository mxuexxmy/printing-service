package xyz.mxue.printing.service.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页功能
 * @author mxuexxmy
 * @date 12/6/2020$ 11:28 PM$
 */
@Data
public class PageInfo<T> implements Serializable {

    private int draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<T> data;
    private String error;

}
