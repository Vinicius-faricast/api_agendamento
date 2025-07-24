package com.exemplo.agendamentoServicos.controller;

import com.exemplo.agendamentoServicos.DTO.RequestProductDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseClientDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseProductDTO;
import com.exemplo.agendamentoServicos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseProductDTO> createProduct(@RequestBody RequestProductDTO dto) {
        ResponseProductDTO productCreate = service.createProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    ;

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> listProduct() {
        List<ResponseProductDTO> listProduct = service.listAllProduct();

        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseProductDTO> ProductById(@PathVariable long id){
        try {
            ResponseProductDTO product = service.listProductById(id);
            return ResponseEntity.ok(product);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseProductDTO> updateProduct(@PathVariable long id, @RequestBody RequestProductDTO body) {
        try {
            ResponseProductDTO updateProduct = service.updateProduct(id, body);
            return ResponseEntity.ok(updateProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        try {
            service.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
