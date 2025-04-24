package com.npro.UserManagementService.Repositories;
import com.npro.UserManagementService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
