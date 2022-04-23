package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String nom;
    @Column(name = "code_postale")
    private String codePostale;
    @ManyToMany(mappedBy = "communes")
    private Set<Livreur> livreurs;
}
