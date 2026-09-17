package com.zosh.repository;

import com.zosh.modal.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business,Long> {
    Business findByOwnerId(Long ownerId);
    @Query("SELECT s FROM Business s WHERE " +
            "(LOWER(s.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.address) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "s.active = true")
    List<Business> searchBusinesses(@Param("keyword") String keyword);
}
