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

/**

 The SondageController class serves as a controller for managing surveys.

 It handles GET and POST requests for displaying, adding, updating, and deleting surveys.
 */
@Controller
public class SondageController {

        private RestTemplate restTemplate;


    /**

     Displays the home page with a list of all surveys.

     @param model a Model object to add attributes to

     @return the name of the HTML template to be rendered
     */
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


    /**

     Displays a single survey by its ID.
     @param model a Model object to add attributes to
     @param id the ID of the survey to be displayed
     @return the name of the HTML template to be rendered
     */
        @GetMapping ("/sondages/{id}")
        public String getSurvey(Model model, @PathVariable Long id){
            this.restTemplate = new RestTemplate();
            String url="http://localhost:8080/rest/sondages/{id}";
            ResponseEntity<Sondage> response =restTemplate.getForEntity(url, Sondage.class, id);
            Sondage sondage = response.getBody();
            model.addAttribute("sondage", sondage);
            return "reponse";
        }

    /**

     Displays the form for adding a new survey.
     @param model a Model object to add attributes to
     @return the name of the HTML template to be rendered
     */
        @GetMapping("/sondages/form/add")
        public String displayFormSurvey(Model model) {
            Sondage sondage = new Sondage();
            model.addAttribute("sondage", sondage);
            return "form";
        }

    /**

     Adds a new survey to the database.
     @param sondage the Sondage object to be added
     @return a redirect to the home page
     */
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

    /**

     Displays the form for updating a survey by its ID.
     @param model a Model object to add attributes to
     @param id the ID of the survey to be updated
     @return the name of the HTML template to be rendered
     */
        @GetMapping("/sondages/update/{id}")
        public String displayFormModSurvey(Model model, @PathVariable Long id){
            this.restTemplate = new RestTemplate();
            String url="http://localhost:8080/rest/sondages/{id}";
            ResponseEntity<Sondage> response = restTemplate.getForEntity(url, Sondage.class, id);
            Sondage sondage = response.getBody();
            model.addAttribute("sondage", sondage);
            return "form";
        }

    /**
     * This method updates a survey using a PUT HTTP request to the server
     * @param sondage The updated survey data
     * @param id The ID of the survey to update
     * @return A String representing the redirect URL for the updated survey
     */

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

    /**
     * This method deletes a survey using a DELETE HTTP request to the server
     * @param model The Model object used to store data for the view
     * @param id The ID of the survey to delete
     * @return A String representing the redirect URL after the survey has been deleted
     */
        @GetMapping ("sondages/delete/{id}")
        public String delSurvey(Model model, @PathVariable Long id){
            this.restTemplate = new RestTemplate();
            String url="http://localhost:8080/rest/sondages/{id}";
            restTemplate.delete(url, id);
            return "redirect:/";
        }

    @GetMapping("/confirmation")
    public String displayRegistrationMessage() {
        return "confirmation";
    }
}
