package br.com.renata.sheout.domain.client;

import br.com.renata.sheout.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity(name = "client")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Client extends User {

    @NotBlank( message = "CPF field can't be empty")
    @Pattern(regexp = "[0-9] {10,11}", message = "Invalid CPF format")
    @Column(length = 11)
    private String cpf;

    @NotBlank( message = "ZipCode field can't be empty")
    @Pattern(regexp = "[0-9] {8}", message = "Invalid ZipCode format")
    @Column(length = 8)
    private String zipCode;
}
