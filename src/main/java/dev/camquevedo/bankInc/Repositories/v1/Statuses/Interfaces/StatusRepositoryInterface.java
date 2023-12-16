package dev.camquevedo.bankInc.Repositories.v1.Statuses.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepositoryInterface extends JpaRepository<Status, Long> {}
