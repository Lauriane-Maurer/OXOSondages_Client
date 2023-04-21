package fr.simplon.oxosondages_client.entity;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Sondage {

    private Long id;

    private String description;

    private String question;


    private LocalDate dateCreation;


    private LocalDate dateCloture;

    private String createur;

    public Sondage() {
    }

    public Sondage(String description, String question, LocalDate dateCreation, LocalDate dateCloture, String createur) {
        this.description = description;
        this.question = question;
        this.dateCreation = dateCreation;
        this.dateCloture = dateCloture;
        this.createur = createur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    private void setDateCreation() {
        this.dateCreation = LocalDate.now();
    }

    public LocalDate getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }



}
