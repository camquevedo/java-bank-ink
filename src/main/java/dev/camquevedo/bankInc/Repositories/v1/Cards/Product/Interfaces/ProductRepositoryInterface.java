package dev.camquevedo.bankInc.Repositories.v1.Cards.Product.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryInterface extends JpaRepository<Product, Long> {}
