package bg.fmi.cms.controller;

import bg.fmi.cms.model.SymmetricKey;
import bg.fmi.cms.service.BinService;
import bg.fmi.cms.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/keys")
public class KeyManagementController {
    @Autowired
    KeyService keyService;

    @Autowired
    BinService binService;

    @GetMapping("")
    public String showAllKeys(Model model) {
        model.addAttribute("symmetricKeyList", keyService.getAll());
        model.addAttribute("symKey", new SymmetricKey());
        return "keys";
    }

    @GetMapping("/{ID}")
    public String showKeyDetails(Model model, @PathVariable("ID") Long id) {
        SymmetricKey key = keyService.getById(id);
        model.addAttribute("symKey", key);
        model.addAttribute("binList", binService.getAll());
        model.addAttribute("isNew", false);
        return "edit-key";
    }

    @DeleteMapping("/{ID}")
    public String deleteKey(@PathVariable("ID") long id) {
        keyService.delete(id);
        return "redirect:keys";
    }

    @PutMapping("")
    public String updateKey(SymmetricKey key) {
        keyService.save(key);
        return "redirect:keys";
    }

    @PostMapping("")
    public String submitNewKey(SymmetricKey key) {
        keyService.save(key);
        return "redirect:keys";
    }

    @GetMapping("/new")
    public String addNewKeyForm(Model model) {
        model.addAttribute("binList", binService.getAll());
        model.addAttribute("symKey", new SymmetricKey());
        model.addAttribute("isNew", true);
        return "edit-key";
    }
}
