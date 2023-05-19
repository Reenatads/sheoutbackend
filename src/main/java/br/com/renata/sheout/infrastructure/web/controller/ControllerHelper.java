package br.com.renata.sheout.infrastructure.web.controller;

import br.com.renata.sheout.domain.store.StoreCategory;
import br.com.renata.sheout.domain.store.StoreCategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.List;


public class ControllerHelper {
    public static void setEditMode(Model model, boolean isEdit){
        model.addAttribute("editMode", isEdit);
    }
    public static void addCategoriesToRequest(StoreCategoryRepository repository, Model model) {
        List<StoreCategory> categories = repository.findAll(Sort.by("name"));
        model.addAttribute("categories", categories);
    }
}
