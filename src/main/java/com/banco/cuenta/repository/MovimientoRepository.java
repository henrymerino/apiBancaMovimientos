package com.banco.cuenta.repository;

import com.banco.cuenta.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
  //  List<Movimiento> findByCuentaId(Long cuentaId);

    @Query("""
        SELECT COALESCE(SUM(ABS(m.valor)),0)
        FROM Movimiento m
        WHERE m.cuenta.id = :cuentaId
        AND DATE(m.fecha) = :fecha
        AND m.valor < 0
    """)
    BigDecimal obtenerRetirosDelDia(
            Long cuentaId,
            LocalDate fecha);

    List<Movimiento> findByCuentaIdAndFechaBetween(
            Long cuentaId,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);
}
