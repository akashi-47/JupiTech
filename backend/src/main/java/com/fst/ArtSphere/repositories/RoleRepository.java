package com.fst.ArtSphere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fst.ArtSphere.entities.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
