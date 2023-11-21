package com.yshanchui.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@TableName("t_order")
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long uid;

    private String username;

    private Long pid;

    private String productName;

    private Double productPrice;

    private  Integer number;
}
