package com.inventory.inventory.repository;

import com.inventory.inventory.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {
}
