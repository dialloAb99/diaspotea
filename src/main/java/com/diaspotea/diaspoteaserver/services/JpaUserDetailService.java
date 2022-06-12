package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Utilisateur;
import com.diaspotea.diaspoteaserver.repository.UtilisateurRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class JpaUserDetailService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    public JpaUserDetailService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur=utilisateurRepository.findByEmail(username);
        if (utilisateur==null){
            throw new UsernameNotFoundException("l'utilisateur n'existe pas");
        }
        Set<GrantedAuthority>authorities=new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(utilisateur.getRole()));

        return new User(utilisateur.getEmail(),utilisateur.getModePasse(),authorities);



    }
}
