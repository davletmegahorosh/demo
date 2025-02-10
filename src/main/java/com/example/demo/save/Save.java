package com.example.demo.save;
import com.example.demo.product.Product;
import com.example.demo.user.User;
import jakarta.persistence.*;

@Entity
@Table
public class Save {

    @Id
    @SequenceGenerator(
            name = "saves",
            sequenceName = "saves",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "saves"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Save() {}
}
