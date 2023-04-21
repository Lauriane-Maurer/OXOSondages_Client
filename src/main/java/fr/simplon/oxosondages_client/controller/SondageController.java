package fr.simplon.oxosondages_client.controller;

import fr.simplon.oxosondages_client.entity.Sondage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Controller
public class SondageController {

        private RestTemplate restTemplate;

        @GetMapping("/")
        public String index(Model model){
            this.restTemplate = new RestTemplate();
            String url ="http://localhost:8080/rest/sondages";
            ResponseEntity<List<Sondage>> response=restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Sondage>>(){});

            List<Sondage> sondages = response.getBody();

            model.addAttribute("sondages", sondages);
            return "index";
        }

        @GetMapping ("/sondages/{id}")
        public String getSurvey(Model model, @PathVariable Long id){
            this.restTemplate = new RestTemplate();
            String url="http://localhost:8080/rest/sondages/{id}";
            ResponseEntity<Sondage> response =restTemplate.getForEntity(url, Sondage.class, id);
            Sondage sondage = response.getBody();
            model.addAttribute("sondage", sondage);
            return "sondage";
        }

        @GetMapping("/sondages/form/add")
        public String displayFormSurvey(Model model) {
            Sondage sondage = new Sondage();
            model.addAttribute("sondage", sondage);
            return "form";
        }

        @PostMapping("/sondages/form/add")
        public String addSurvey(@ModelAttribute("sondage") Sondage sondage){
            this.restTemplate = new RestTemplate();
            String url ="http://localhost:8080/rest/sondages";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Sondage> request = new HttpEntity<>(sondage, headers);
            ResponseEntity<Sondage> response = restTemplate.postForEntity(url, request, Sondage.class);
            return "redirect:/";
        }


        @GetMapping("/sondages/update/{id}")
        public String displayFormModSurvey(Model model, @PathVariable Long id){
            this.restTemplate = new RestTemplate();
            String url="http://localhost:8080/rest/sondages/{id}";
            ResponseEntity<Sondage> response = restTemplate.getForEntity(url, Sondage.class, id);
            Sondage sondage = response.getBody();
            model.addAttribute("sondage", sondage);
            return "form";
        }

        @PostMapping("/sondages/update/{id}")
        public String updateStudent (@ModelAttribute("sondage")Sondage sondage, @PathVariable Long id) {
            this.restTemplate = new RestTemplate();
            String url = "http://localhost:8080/rest/sondages/{id}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Sondage> request = new HttpEntity<>(sondage, headers);
            ResponseEntity<Sondage> response = restTemplate.exchange(url, HttpMethod.PUT, request, Sondage.class, id);
            return "redirect:/";
        }

        @GetMapping ("sondages/delete/{id}")
        public String delSurvey(Model model, @PathVariable Long id){
            this.restTemplate = new RestTemplate();
            String url="http://localhost:8080/rest/sondages/{id}";
            restTemplate.delete(url, id);
            return "redirect:/";
        }
}
