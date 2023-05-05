package br.com.renata.sheout.domain.client;

import br.com.renata.sheout.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity(name = "client")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Client extends User {
    private String cpf;
    private String zipCode;
}
