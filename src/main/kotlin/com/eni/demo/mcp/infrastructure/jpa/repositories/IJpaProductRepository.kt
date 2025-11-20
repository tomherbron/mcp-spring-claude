package com.eni.demo.mcp.infrastructure.jpa.repositories

import com.eni.demo.mcp.infrastructure.jpa.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * JPA Repository for ProductEntity
 */
@Repository
interface IJpaProductRepository : JpaRepository<ProductEntity, UUID> {}