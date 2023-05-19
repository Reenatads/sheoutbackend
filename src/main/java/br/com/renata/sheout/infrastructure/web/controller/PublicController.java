package br.com.renata.sheout.infrastructure.web.controller;

import br.com.renata.sheout.application.service.ClientService;
import br.com.renata.sheout.application.service.StoreService;
import br.com.renata.sheout.application.service.ValidationException;
import br.com.renata.sheout.domain.client.Client;
import br.com.renata.sheout.domain.store.Store;
import br.com.renata.sheout.domain.store.StoreCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/public")
public class PublicController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreCategoryRepository storeCategoryRepository;

    @GetMapping("/client/new")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        ControllerHelper.setEditMode(model, false);
        return "clientRegister";
    }

    @GetMapping("/store/new")
    public String newStore(Model model) {
        model.addAttribute("store", new Store());
        ControllerHelper.setEditMode(model, false);
        ControllerHelper.addCategoriesToRequest(storeCategoryRepository, model);
        return "storeRegister";
    }
    @PostMapping(path = "/client/save")
    public String saveClient(
            @ModelAttribute("client") @Valid Client client,
            Errors errors,
            Model model) {
        if (!errors.hasErrors()){
            try{
                clientService.saveClient(client);
                model.addAttribute("msg", "Client saved successfully");
            } catch (ValidationException validationException) {
                errors.rejectValue("email", null,validationException.getMessage() );
            }
        }

        ControllerHelper.setEditMode(model, false);
        return "clientRegister";
    }
    @PostMapping(path = "/store/save")
    public String saveStore(
            @ModelAttribute("store") @Valid Store store,
            Errors errors,
            Model model) {

        if (!errors.hasErrors()) {
            try {
                storeService.saveStore(store);
                model.addAttribute("msg", "Store successfully saved!");

            } catch (ValidationException validationException) {
                errors.rejectValue("email", null, validationException.getMessage());
            }
        }
        ControllerHelper.setEditMode(model, false);
        ControllerHelper.addCategoriesToRequest(storeCategoryRepository, model);
        return "storeRegister";
    }
}
