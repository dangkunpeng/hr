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
public class HrProject implements Serializable {

    @Id
    private String projectId;
    @Column
    private String projectName;
    @Column
    private String projectGroupEid;
    @Column
    private String peopleEid;
    @Column
    private String wbsCode;
    @Column
    private String tePeriod;
}
