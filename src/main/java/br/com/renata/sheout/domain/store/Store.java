package br.com.renata.sheout.domain.store;

import br.com.renata.sheout.domain.user.User;
import br.com.renata.sheout.infrastructure.web.validator.UploadConstraint;
import br.com.renata.sheout.util.FileType;
import br.com.renata.sheout.util.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity(name = "store")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Store extends User {
    @NotBlank(message = "O CNPJ não pode ser vazio")
    @Pattern(regexp = "[0-9] {14}", message = "Invalid CNPJ format")
    @Column(length = 14, nullable = false)
    private String cnpj;
    @Size(max = 80)
    private String logo;
    @UploadConstraint(acceptedTypes = FileType.PNG, message = "File not valid")
    private transient MultipartFile logoFile;
    @NotNull(message = "Delivery tax field can't be empty")
    @Min(0)
    @Max(99)
    private BigDecimal deliveryTax;

    @NotNull(message = "Delivery time field can't be empty")
    @Min(0)
    @Max(120)
    private Integer deliveryTime;

    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(
            name = "store_has_category",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "category_store_id")
    )
    @Size(min = 1, message = "The store should have at least one category")
    @ToString.Exclude
    private Set<StoreCategory> categories = new HashSet<>(0);

    @OneToMany(mappedBy = "store")
    private Set<ItemStock> itensStock = new HashSet<>(0);

    public void setLogoFileName() {
        if (getId() == null) {
            throw new IllegalStateException("First you should save th registry");
        }

        this.logo = String.format("%04d-logo.%s", getId(), FileType.of(logoFile.getContentType()).getExtension());
    }

    public String getCategoriesAsText() {
        Set<String> strings = new LinkedHashSet<>();

        for (StoreCategory category : categories) {
            strings.add(category.getName());
        }

        return StringUtils.concatenate(strings);
    }

//    public Integer calcularTempoEntrega(String cep) {
//        int soma = 0;
//
//        for (char c : cep.toCharArray()) {
//            int v = Character.getNumericValue(c);
//            if (v > 0) {
//                soma += v;
//            }
//        }
//
//        soma /= 2;
//
//        return tempoEntregaBase + soma;
//    }

}
