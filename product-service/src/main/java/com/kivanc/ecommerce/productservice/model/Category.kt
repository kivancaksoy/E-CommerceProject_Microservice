package com.kivanc.ecommerce.productservice.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "categories")
data class Category @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val categoryName: String,

    /*@OneToMany(mappedBy = "category")
    val products: Set<Product>*/

)
