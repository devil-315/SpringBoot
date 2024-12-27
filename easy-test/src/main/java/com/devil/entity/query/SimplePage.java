package com.devil.entity.query;

import com.devil.entity.enums.PageSize;
import lombok.Getter;
import lombok.ToString;

/**
 * ClassName：SimplePage
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
@Getter
@ToString
public class SimplePage {
    //当前页
    private Integer pageNo;

    //总记录数
    private int countTotal;

    //每页显示数量
    private int pageSize;

    //总页数
    private int pageTotal;

    //当前页的起始索引
    private int start;

    public SimplePage() {
    }

    public SimplePage(int start) {
        this.start = start;
    }

    public SimplePage(Integer pageNo, int countTotal, int pageSize) {
        if (pageNo == null){
            pageNo = 0;
        }
        this.pageNo = pageNo;
        this.countTotal = countTotal;
        this.pageSize = pageSize;
        action();
    }

    public void action(){
        if(this.pageSize <= 0 ){
            this.pageSize = PageSize.SIZE5.getSize();
        }

        //TODO 如果 总记录数 > 0，计算总页数，否则默认总页数为 1
        if(this.countTotal > 0){
            this.pageTotal = (int) Math.ceil((double) this.countTotal/this.pageSize);
        }else {
            this.pageTotal = 1;
        }

        //TODO 如果 当前页 <= 1， 默认 是 第一页
        if (pageNo <= 1){
            pageNo = 1;
        }

        //TODO 如果当前页 大于 总页数，让当前页 = 最后一页
        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }

        //计算起始索引
        this.start = (pageNo - 1) * pageSize;
    }
}
