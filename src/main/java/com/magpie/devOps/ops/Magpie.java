package com.magpie.devOps.ops;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.magpie.devOps.common.EntityParam;

import lombok.Data;

@Data
@Entity
@Table(name="Day_desc")
public class Magpie extends EntityParam implements Serializable, Comparable<Magpie> {
    private static final long serialVersionUID = 1563609999036880127L;
    @Id
    private String uuid;
    @Column
    private String day;

    @Column
    private String desc;

    @Override
    public int compareTo(Magpie o) {
        return o.getUuid().compareTo(this.uuid);
    }
}
