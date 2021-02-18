package bg.fmi.cms.controller;

import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.BinRange;
import bg.fmi.cms.service.BinRangeService;
import bg.fmi.cms.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bin-management")
public class BinManagementController {
    @Autowired
    BinRangeService binRangeService;
    @Autowired
    BinService binService;

    @GetMapping("")
    public String showAllBinRanges(Model model) {
        model.addAttribute("binRangeList", binRangeService.getAll());
        return "bin-management";
    }

    @GetMapping("/new")
    public String addNewBinRange(Model model) {
        model.addAttribute("binRange", new BinRange());
        model.addAttribute("bin", null);
        model.addAttribute("rangeId", 0);
        return "edit-bin";
    }

    @PostMapping("")
    public String addBinRange(BinRange binRange) {
        binRangeService.addBinRange(binRange);
        return "redirect:bin-management";
    }

    @DeleteMapping("{id}")
    public String deleteBinRange(@PathVariable("id") Long id) {
        BinRange binRange = new BinRange();
        binRange.setId(id);
        binRangeService.removeBinRange(binRange);
        return "redirect:bin-management";
    }

    @GetMapping("/{id}/bins")
    public String getBinsForBin(Model model, @PathVariable("id") long id) {
        model.addAttribute("binList", binRangeService.getBinsInRange(id));
        model.addAttribute("binRange", binRangeService.getRangeById(id));
        return "bins";
    }

    @GetMapping("/{id}/bins/new")
    public String addNewBin(Model model, @PathVariable("id") Long id) {
        model.addAttribute("binRange", null);
        model.addAttribute("bin", new Bin());
        model.addAttribute("rangeId", id);
        return "edit-bin";
    }

    @PostMapping("/{id}/bins")
    public String addBinToRange(@PathVariable("id") long id, Bin bin) {
        binRangeService.addBinInRange(id, bin);
        return "redirect:bins";
    }

    @DeleteMapping("/{id}/bins/{bin-id}")
    public String deleteBin(@PathVariable("bin-id") long binId, @PathVariable("id") long id) {
        binService.delete(binId);
        return "redirect:bins";
    }


}
