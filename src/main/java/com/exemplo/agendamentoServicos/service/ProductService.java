package com.exemplo.agendamentoServicos.service;

import com.exemplo.agendamentoServicos.DTO.ResponseProductDTO;
import com.exemplo.agendamentoServicos.entity.Product;
import com.exemplo.agendamentoServicos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public ResponseProductDTO toResponseDTO(Product product){
        return new ResponseProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getComission(),
                product.getDuration()
        );
    }

    public List<ResponseProductDTO> listAllProduct(){
        return repository
                .findAll()
                .stream()
                .filter(Product::isActive)
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ResponseProductDTO listProductById(Long id){
        return repository
                .findById(id)
                .filter(Product::isActive)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("serviço não encontrado"));
    }
}
