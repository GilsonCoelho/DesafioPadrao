package br.com.gsc.addressAPI.repository;

import br.com.gsc.addressAPI.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
