package br.com.renata.sheout.domain.user;

import br.com.renata.sheout.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class User implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name field can't be empty")
    @Size(max = 80, message = "The name is too long" )
    private String name;

    @NotBlank(message = "E-mail field can't be empty")
    @Size(max = 60, message = "The e-mail is too long" )
    @Email(message = "This is not a valid e-mail")
    private String email;

    @NotBlank(message = "Password field can't be empty")
    @Size(max = 60, message = "The e-mail is too long" )
    private String password;

    @NotBlank(message = "PhoneNumber field can't be empty")
    @Pattern(regexp = "[0-9]{10,11}", message = "Invalid phone number format")
    @Column(length = 11, nullable = false)
    private String phoneNumber;

    public void encryptPassword(){
        this.password = StringUtils.encrypt(this.password);
    }
}
