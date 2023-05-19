package br.com.renata.sheout.application.service;

import br.com.renata.sheout.domain.client.Client;
import br.com.renata.sheout.domain.client.ClientRepository;
import br.com.renata.sheout.domain.store.Store;
import br.com.renata.sheout.domain.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ImageService imageService;

    @Transactional
    public void saveStore(Store store) throws ValidationException {
        if (!validateEmail(store.getEmail(), store.getId())) {
            throw new ValidationException("Duplicated e-mail");
        }

        if (store.getId() != null) {
            Store storeDb = storeRepository.findById(store.getId()).orElseThrow(NoSuchElementException::new);
            store.setPassword(storeDb.getPassword());
            store.setLogo(storeDb.getLogo());
            storeRepository.save(store);

        } else {
            store.encryptPassword();
            store = storeRepository.save(store);
            store.setLogoFileName();
            imageService.uploadLogo(store.getLogoFile(), store.getLogo());
        }
    }

    private boolean validateEmail(String email, Long id) {
        Client client = clientRepository.findByEmail(email);

        if (client != null) {
            return false;
        }

        Store store = storeRepository.findByEmail(email);

        if (store != null) {
            if (id == null) {
                return false;
            }

            if(!store .getId().equals(id)) {
                return false;
            }
        }

        return true;
    }
}
