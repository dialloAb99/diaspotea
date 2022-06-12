package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.controller.validator.PasswordValueMatch;
import com.diaspotea.diaspoteaserver.controller.validator.ValidEmail;
import com.diaspotea.diaspoteaserver.controller.validator.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
public class UtilisateurInscriptionDto implements Serializable {
    @ValidEmail
    @NotBlank
    private  String email;
    @NotBlank
    private  String nom;
    @NotBlank
    private  String prenom;
    @NotBlank
    @ValidPassword
    private   String password;
    @NotBlank
    @ValidPassword
    private   String confirmPassword;

}
