package com.miguez.sancristobal.repository;

import com.miguez.sancristobal.dtos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends JpaRepository<Usuario,Long> {
     Usuario findUserByEmail(String email);
}
