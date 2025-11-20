package com.eni.demo.mcp.infrastructure.jpa.repositories

import com.eni.demo.mcp.infrastructure.jpa.model.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * JPA Repository for CartEntity
 */
@Repository
interface IJpaCartRepository : JpaRepository<CartEntity, UUID> {}