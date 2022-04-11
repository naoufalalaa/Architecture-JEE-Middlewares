package ma.enset.etudiantsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.etudiantsmvc.entities.Etudiant;
import ma.enset.etudiantsmvc.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class EtudiantController {
    private EtudiantRepository etudiantRepository;
    @GetMapping("/index")
    public String etudiants(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword) throws Exception{
        if(page<0) {
            return "redirect:/index?page=" + 0 + "&keyword=" + keyword;
        }
        Page<Etudiant> pageEtudiant=etudiantRepository.findByNom(keyword, PageRequest.of(page,size));
        if(page>pageEtudiant.getTotalPages()){
            return "redirect:/index?page="+(pageEtudiant.getTotalPages()-1)+"&keyword="+keyword;
        }
        model.addAttribute("etudiants",pageEtudiant.getContent());
        model.addAttribute("pages",new int[pageEtudiant.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "etudiants";
    }
    @GetMapping("/delete")
    public String delete(String id, String keyword, int page) {
        etudiantRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/etudiants")
    @ResponseBody
    public List<Etudiant> listEtudiants(){
        return etudiantRepository.findAll();
    }

    @GetMapping("/formEtudiant")
    public String formEtudiant(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "formEtudiant";
    }

    @GetMapping("/editEtudiant")
    public String editEtudiant(Model model,String id,String keyword,int page){
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        System.out.println(etudiant.getNom());
        if (etudiant==null) throw new RuntimeException("Etudiant introuvable");
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editEtudiant";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid Etudiant etudiant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formEtudiant";
        etudiantRepository.save(etudiant);
        System.out.println(etudiant.getId());
        System.out.println(etudiant.getNom());
        return "redirect:/index?page"+page+"&keyword"+keyword;
    }
}
