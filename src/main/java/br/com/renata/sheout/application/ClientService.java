package br.com.renata.sheout.application;

import br.com.renata.sheout.domain.client.Client;
import br.com.renata.sheout.domain.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) {
        clientRepository.save(client);
    }
}
