package com.example.gccoffee.controller;

import com.example.gccoffee.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String productsPage(Model model) {
        var products = service.getAllProducts();
        model.addAttribute("products", products);

        return "product-list";
    }

    @GetMapping("new-product")
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping("/products")
    public String newProduct(CreateProductRequest createProductRequest) {
        service.createProduct(createProductRequest.productName(), createProductRequest.category(),
            createProductRequest.price(), createProductRequest.description());
        return "redirect:/products";
    }
}
