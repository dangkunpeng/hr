package com.magpie.devOps.hr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HrMenu implements Serializable {

    @Id
    private String menuId;
    @Column
    private String menuName;
    @Column
    private String menuGroup;
    @Column
    private String menuIndex;
}
