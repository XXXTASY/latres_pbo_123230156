/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Inheritance and Polymorphism applied here
 * @author XXXTASY
 */
public class WebDeveloper extends Candidate {
    private static final double WRITING_WEIGHT = 0.4;
    private static final double CODING_WEIGHT = 0.35;
    private static final double INTERVIEW_WEIGHT = 0.25;
    private static final double MIN_FINAL_SCORE = 85.0;

    public WebDeveloper(String name, double writingScore, double codingScore, double interviewScore) {
        super(name, "Web Developer", writingScore, codingScore, interviewScore);
        this.finalScore = calculateFinalScore();
        this.status = getSelectionStatus();
    }

    public WebDeveloper(int id, String name, double writingScore, double codingScore, double interviewScore) {
        super(id, name, "Web Developer", writingScore, codingScore, interviewScore);
        this.finalScore = calculateFinalScore();
        this.status = getSelectionStatus();
    }

    @Override
    public double calculateFinalScore() {
        return (writingScore * WRITING_WEIGHT) +
               (codingScore * CODING_WEIGHT) +
               (interviewScore * INTERVIEW_WEIGHT);
    }

    @Override
    public String getSelectionStatus() {
        if (finalScore >= MIN_FINAL_SCORE) {
            return "DITERIMA";
        } else {
            return "TIDAK DITERIMA";
        }
    }
}
