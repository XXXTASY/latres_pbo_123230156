/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Inheritance and Polymorphism applied here
 * @author XXXTASY
 */
public class AndroidDeveloper extends Candidate {
    private static final double WRITING_WEIGHT = 0.2;
    private static final double CODING_WEIGHT = 0.5;
    private static final double INTERVIEW_WEIGHT = 0.3;
    private static final double MIN_FINAL_SCORE = 85.0;

    public AndroidDeveloper(String name, double writingScore, double codingScore, double interviewScore) {
        super(name, "Android Developer", writingScore, codingScore, interviewScore);
        this.finalScore = calculateFinalScore();
        this.status = getSelectionStatus();
    }

    public AndroidDeveloper(int id, String name, double writingScore, double codingScore, double interviewScore) {
        super(id, name, "Android Developer", writingScore, codingScore, interviewScore);
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
