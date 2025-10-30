package se.apiva.hexdemo.adapter.inventory.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, String> {
}
