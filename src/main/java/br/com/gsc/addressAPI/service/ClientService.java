package br.com.gsc.addressAPI.service;

import br.com.gsc.addressAPI.model.Client;

/**
 * Interface that defines the standard <b>Strategy</b> in the customer domain. With
 * that, if necessary, we can have multiple implementations of the same
 *interface.
 *
 * @author GilsonCoelho
 */
public interface ClientService {

    Iterable<Client> fetchAll();

    Client findById(Long id);

    void insert(Client client);

    void update(Long id, Client client);

    void delete(Long id);
}
