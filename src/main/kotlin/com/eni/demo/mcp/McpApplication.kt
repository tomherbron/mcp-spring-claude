package com.eni.demo.mcp

import com.eni.demo.mcp.infrastructure.mcp.claude.adapters.CartMcpAdapter
import com.eni.demo.mcp.infrastructure.mcp.claude.adapters.ProductMcpAdapter
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackages = ["com.eni.demo.mcp"])
class McpApplication {

    @Bean
    fun mcpTools(cartMcpAdapter: CartMcpAdapter, productMcpAdapter: ProductMcpAdapter): ToolCallbackProvider {
        return MethodToolCallbackProvider.builder()
            .toolObjects(cartMcpAdapter, productMcpAdapter)
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<McpApplication>(*args)
}

