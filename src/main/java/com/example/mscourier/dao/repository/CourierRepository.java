package com.example.mscourier.dao.repository;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.enums.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long>, JpaSpecificationExecutor<Courier> {
    List<Courier> findByStatus(CourierStatus status);

    @Query("SELECT c FROM Courier c JOIN FETCH c.profile WHERE c.id = :id")
    Optional<Courier> findByIdWithProfile(@Param("id") Long id);

    @Query("SELECT c FROM Courier c JOIN FETCH c.profile")
    List<Courier> findAllWithProfile();

    @Query("SELECT c FROM Courier c JOIN FETCH c.profile WHERE c.status = :status")
    List<Courier> findAllByStatusWithProfile(@Param("status") CourierStatus status);
}
