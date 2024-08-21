package com.inventory.inventory.service.impl;

import com.inventory.inventory.dto.BorrowingDTO;
import com.inventory.inventory.entity.Borrowing;
import com.inventory.inventory.entity.Product;
import com.inventory.inventory.entity.User;
import com.inventory.inventory.exception.RequestTidakSesuai;
import com.inventory.inventory.exception.ResourceNotFoundException;
import com.inventory.inventory.repository.BorrowingRepository;
import com.inventory.inventory.repository.ProductRepository;
import com.inventory.inventory.repository.UserRepository;
import com.inventory.inventory.service.IBorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements IBorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void borrowTool(BorrowingDTO borrowingDTO) {

        // find user
        User userById = userRepository.findById(borrowingDTO.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id", borrowingDTO.getUserId().toString())
        );

        // find product
        Product productById = productRepository.findById(borrowingDTO.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Product","id", borrowingDTO.getProductId().toString())
        );

        // cek apakah pinjaman lebih besar dari stok di inventory product
        if (productById.getQuantity() < borrowingDTO.getQuantity()) {
            throw new RequestTidakSesuai("Pinjaman melebihi stok");
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setUser(userById);
        borrowing.setProduct(productById);
        borrowing.setQuantity(borrowingDTO.getQuantity());

        borrowingRepository.save(borrowing);

        // update stok inventory product
        productById.setQuantity(productById.getQuantity() - borrowingDTO.getQuantity());
        productRepository.save(productById);

    }

    @Override
    public void returnBorrow(Long borrowId) {


        Borrowing borrowing = borrowingRepository.findById(borrowId).orElseThrow(
                () -> new ResourceNotFoundException("Borrow","id", borrowId.toString())
        );

        Product product = productRepository.findById(borrowing.getProduct().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Product","id", borrowing.getProduct().getId().toString())
        );

        product.setQuantity(product.getQuantity() + borrowing.getQuantity());
        productRepository.save(product);

        borrowingRepository.deleteById(borrowId);

    }
}
