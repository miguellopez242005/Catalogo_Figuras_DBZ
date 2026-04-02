package com.catalogo_dbz.Catalogo_dbz.repository;
import com.catalogo_dbz.Catalogo_dbz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}