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
public class HrPeople implements Serializable {

    @Id
    private String peopleEid;
    @Column
    private String peopleName;
    @Column
    private String peopleGroupEid;
    @Column
    private String status;
}
