package com.marcus.chiu.springmvc.e_entity.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by marcus.chiu on 10/21/16.
 */
@Entity
@Table(name="POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false)
    private int id;

    @Size(max=100)
    @Column(name = "text", nullable = false)
    private String text;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) //same object
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof Post))
            return false;
        Post other = (Post) obj;
        if(id != other.id)
            return false;
        if(text != other.text)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", text=" + text + "]";
    }
}
