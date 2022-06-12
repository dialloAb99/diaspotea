package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.dto.TypeProduitDto;
import com.diaspotea.diaspoteaserver.models.Type;
import com.diaspotea.diaspoteaserver.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TypeService {
    private final TypeRepository typeRepository;
    @Autowired
    public TypeService(TypeRepository repository){
        typeRepository=repository;
    }

    public Type ajouterUnType(Type type) {
        return typeRepository.save(type);
    }

    public Type getType(int id) {
        return typeRepository.getById(id);
    }

    public Type modifierType(Type typeRecupere) {
        return typeRepository.save(typeRecupere);
    }

    public void deleteType(int id) {
        typeRepository.deleteById(id);
    }

    public List<TypeProduitDto> getAll() {
        return  typeRepository.findAllTypeProduitDto();
    }

}
