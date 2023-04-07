package br.com.gsc.addressAPI.service.impl;

import br.com.gsc.addressAPI.model.Address;
import br.com.gsc.addressAPI.model.Client;
import br.com.gsc.addressAPI.repository.AddressRepository;
import br.com.gsc.addressAPI.repository.ClientRepository;
import br.com.gsc.addressAPI.service.ClientService;
import br.com.gsc.addressAPI.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of <b>Strategy</b> {@link ClientService}, which can be
 * injected by Spring (via {@link Autowired}). With that, as this class is a
 * {@link Service}, it will be treated as a <b>Singleton</b>.
 *
 * @author GilsonCoelho
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;


    @Override
    public Iterable<Client> fetchAll() {
        return clientRepository.findAll();

    }

    @Override
    public Client findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void insert(Client client) {
        saveClientWithCep(client);
    }

    @Override
    public void update(Long id, Client client) {
        Optional<Client> clientBd = clientRepository.findById(id);
        if (clientBd.isPresent()) {
            saveClientWithCep(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private void saveClientWithCep(Client client) {
         String cep = client.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.findCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(address);
         clientRepository.save(client);
    }
}
