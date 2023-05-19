package br.com.renata.sheout.application.service;

import br.com.renata.sheout.domain.client.Client;
import br.com.renata.sheout.domain.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) throws ValidationException {
        if (!validateEmail(client.getEmail(), client.getId())){
            throw new ValidationException("Duplicated e-mail");
        }
        if (client.getId() != null){
            Client clientDb = clientRepository.findById(client.getId()).orElseThrow(NoSuchElementException::new);
            client.setPassword(clientDb.getPassword());
        } else{
            client.encryptPassword();
        }
        clientRepository.save(client);
    }

    private boolean validateEmail(String email, Long id) {
        Client client = clientRepository.findByEmail(email);

        if (client != null){
            if (id == null){
                return false;
            }
            if (!client.getId().equals(id)){
                return false;
            }
        }
        return true;
    }
}
