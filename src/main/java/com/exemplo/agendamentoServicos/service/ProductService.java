package com.exemplo.agendamentoServicos.service;

import com.exemplo.agendamentoServicos.DTO.RequestProductDTO;
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

    public ResponseProductDTO createProduct(RequestProductDTO dto) {
        Product product = new Product(dto);
        repository.save(product);
        return toResponseDTO(product);
    }

    public ResponseProductDTO updateProduct(Long id, RequestProductDTO dto){
        return repository.findById(id)
                .filter(Product::isActive)
                .map(product -> {
                    product.setName(dto.name());
                    product.setPrice(dto.price());
                    product.setComission(dto.comission());
                    product.setDuration(dto.duration());
                    return toResponseDTO(repository.save(product));
                }).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public void deleteProduct(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Produto não encontrado!");
        }

        repository.findById(id).map(product -> {
            product.setActive(false);
            return repository.save(product);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
