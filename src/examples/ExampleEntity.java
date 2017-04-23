package com.marcus.chiu.springmvc._examples;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(indexes = {
    @Index(columnList = "index1", unique = false)})
public class ExampleEntity implements Serializable {

    private static final long serialVersionUID = -5020553474373492996L;

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(nullable = false)
    private String index1;

    @Column(nullable = false)
    private Integer manufacturerID;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private Date requestDate;

    @PrePersist
    void prePersist() {
        this.requestDate = new Date();
    }
}
