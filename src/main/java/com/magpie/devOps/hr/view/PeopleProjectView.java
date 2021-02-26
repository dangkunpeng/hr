package com.magpie.devOps.hr.view;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleProjectView implements Serializable {

    private String peopleEid;
    
    private String peopleName;
    
    private String peopleGroupEid;
    
    private String status;

    private String projectId;
    
    private String projectName;
    
    private String projectGroupEid;
    
    private String wbsCode;
    
    private String tePeriod;
}
