package org.tlw.MyPaperless.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;


@MappedSuperclass
public class AbstractModel {
    private int uid;

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "uid", unique = true)
    public int getUid() {
        return this.uid;
    }

    protected void setUid(int uid) {
        this.uid = uid;
    }
}
