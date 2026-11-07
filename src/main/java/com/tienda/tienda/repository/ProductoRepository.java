package com.tienda.tienda.repository;

import com.tienda.tienda.domain.Producto;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    public List<Producto> findByActivoTrue();
    
    //Consulta derivada que recupera los producto de un rango de precio y los ordena por precio ascendentemente
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(BigDecimal precioInf, BigDecimal precioSup);

    //Consulta JPQL que recupera los producto de un rango de precio y los ordena por precio ascendentemente
    @Query(value = "SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaJPQL(BigDecimal precioInf, BigDecimal precioSup);

    //Consulta SQL que recupera los producto de un rango de precio y los ordena por precio ascendentemente
    @Query(nativeQuery = true,
            value = "SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaSQL(BigDecimal precioInf, BigDecimal precioSup);
    
    ///////PRACTICA 2/////
    ///SE BUSCA HACER UNA BUSQUEDA JPQL POR MEDIO DEL STOCK DE INVENTARIO//////
   
    @Query(value = "SELECT p FROM Producto p WHERE p.existencias > :existencias ORDER BY p.existencias DESC")
    public List<Producto> consultaAmpliadaMas(Integer existencias);
    
    @Query(value = "SELECT p FROM Producto p WHERE p.existencias < :existencias ORDER BY p.existencias DESC")
    public List<Producto> consultaAmpliadaMenos(Integer existencias);
}