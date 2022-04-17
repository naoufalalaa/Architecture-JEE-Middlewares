package ma.enset.etudiantsmvc.web;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
public class EtudiantController {
    private EtudiantRepository etudiantRepository;//repository
    @GetMapping("/user/index")
    public String etudiants(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword) throws Exception{
        if(page<0) {//pour eviter les erreurs
            return "redirect:/user/index?page=" + 0 + "&keyword=" + keyword;
        }
        Page<Etudiant> pageEtudiant=etudiantRepository.findByNomContains(keyword, PageRequest.of(page,size));//pour recuperer les données de l'etudiant
        if(page>pageEtudiant.getTotalPages()){//pour verifier si la page demandé est supérieur au nombre de page
            return "redirect:/user/index?page="+(pageEtudiant.getTotalPages()-1)+"&keyword="+keyword;//pour afficher la derniere page
        }
        model.addAttribute("etudiants",pageEtudiant.getContent());//pour afficher les données de l'etudiant
        model.addAttribute("pages",new int[pageEtudiant.getTotalPages()]);//pour afficher les pages
        model.addAttribute("currentPage",page);//pour afficher la page courante
        model.addAttribute("keyword",keyword);//pour afficher le mot clé
        return "etudiants";
    }
    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page) {//pour supprimer un etudiant
        etudiantRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {//pour afficher la page d'accueil
        return "home";
    }

    @GetMapping("/user/etudiants")//pour recuperer les données de l'etudiant
    @ResponseBody//pour dire que c'est un données json
    public List<Etudiant> listEtudiants(){
        return etudiantRepository.findAll();
    }

    @GetMapping("/admin/formEtudiant") //pour afficher le formulaire
    public String formEtudiant(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "formEtudiant";
    }

    @GetMapping("/admin/editEtudiant") //pour recuperer les données de l'etudiant
    public String editEtudiant(Model model,Long id,String keyword,int page){
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant==null) throw new RuntimeException("Etudiant introuvable");
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editEtudiant";
    }

    @PostMapping("/admin/save") //@RequestBody
    public String save(Model model, @Valid Etudiant etudiant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formEtudiant";
        etudiantRepository.save(etudiant);
        return "redirect:/user/index?page"+page+"&keyword"+keyword;
    }
}
