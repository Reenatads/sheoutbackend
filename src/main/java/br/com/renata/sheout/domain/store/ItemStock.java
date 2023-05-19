package br.com.renata.sheout.domain.store;

import br.com.renata.sheout.infrastructure.web.validator.UploadConstraint;
import br.com.renata.sheout.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "itemStock")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name can't be empty")
    @Size(max =  50)
    private String name;

    @NotBlank(message = "Category can't be empty")
    @Size(max =  25)
    private String category;

    @NotBlank(message = "Description can't be empty")
    @Size(max =  80)
    private String description;

    @Size(max = 50)
    private String image;

    @NotNull(message = "Price can't be empty")
    @Min(0)
    private BigDecimal price;

    @NotNull
    private Boolean featuredProduct;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @UploadConstraint(acceptedTypes = FileType.PNG, message = "This is not a valid image file")
    private transient MultipartFile imagemFile;


    public void setImagemFileName() {
        if (getId() == null) {
            throw new IllegalStateException("First you should create the object");
        }

        this.image = String.format("%04d-comida.%s", getId(), FileType.of(imagemFile.getContentType()).getExtension());
    }
}
