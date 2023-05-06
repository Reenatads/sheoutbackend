package br.com.renata.sheout.controller;

import br.com.renata.sheout.application.service.ClientService;
import br.com.renata.sheout.domain.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/public")
public class PublicController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/client/new")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        return "clientRegister";
    }

    @PostMapping(path = "/client/save")
    public String saveClint(@ModelAttribute("client") Client client) {
        clientService.saveClient(client);
        return "clientRegister";
    }
}
