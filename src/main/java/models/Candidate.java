/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Encapsulation applied here
 * @author XXXTASY
 */
public abstract class Candidate implements IRecruitment {
    protected int id;
    protected String name;
    protected String path;
    protected double writingScore;
    protected double codingScore;
    protected double interviewScore;
    protected double finalScore;
    protected String status;

    public Candidate(String name, String path, double writingScore, double codingScore, double interviewScore) {
        this.name = name;
        this.path = path;
        this.writingScore = Math.max(0, Math.min(100, writingScore));
        this.codingScore = Math.max(0, Math.min(100, codingScore));
        this.interviewScore = Math.max(0, Math.min(100, interviewScore));
    }

    public Candidate(int id, String name, String path, double writingScore, double codingScore, double interviewScore) {
        this(name, path, writingScore, codingScore, interviewScore);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public double getWritingScore() {
        return writingScore;
    }

    public double getCodingScore() {
        return codingScore;
    }

    public double getInterviewScore() {
        return interviewScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setWritingScore(double writingScore) {
        this.writingScore = Math.max(0, Math.min(100, writingScore));
    }

    public void setCodingScore(double codingScore) {
        this.codingScore = Math.max(0, Math.min(100, codingScore));
    }

    public void setInterviewScore(double interviewScore) {
        this.interviewScore = Math.max(0, Math.min(100, interviewScore));
    }
}
