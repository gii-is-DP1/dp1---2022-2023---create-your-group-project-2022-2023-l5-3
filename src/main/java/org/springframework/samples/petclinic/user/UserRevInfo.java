package org.springframework.samples.petclinic.user;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import lombok.Setter;

@Setter
@Entity
@RevisionEntity(UserRevisionListener.class)
public class UserRevInfo extends DefaultRevisionEntity{
    @Column(name = "user")
    private String username;
}
