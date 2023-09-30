package com.kivanc.ecommerce.productservice.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal

@Entity
@Table(name = "products")
data class Product @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val productName: String,
    val details: String,
    val price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    val category: Category,

/*    // TODO productImage oluştur.
    @OneToMany(mappedBy = "product")
    val productImages: Set<ProductImage>*/

)
