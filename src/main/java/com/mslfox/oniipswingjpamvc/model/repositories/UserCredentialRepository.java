package com.mslfox.oniipswingjpamvc.model.repositories;

import com.mslfox.oniipswingjpamvc.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

}