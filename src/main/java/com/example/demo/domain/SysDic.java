package com.example.demo.domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysDic {

    private Integer id;

    private Integer groupId;

    private String code;

    private String name;

    private String parentCode;

    private Integer sort;

    private Integer state;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

}