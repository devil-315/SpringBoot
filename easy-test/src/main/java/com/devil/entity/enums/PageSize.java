package com.devil.entity.enums;

/**
 * ClassName：PageSize
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
public enum PageSize {
    SIZE5(5),SIZE10(10),SIZE15(15),SIZE20(20),SIZE25(25),
    SIZE30(30),SIZE40(40),SIZE50(50);
    int size;

    PageSize() {
    }

    PageSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
