/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import connector.Connector;
import models.AndroidDeveloper;
import models.Candidate;
import models.WebDeveloper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author XXXTASY was helped by GEMINI (for perfecting code)
 */
public class RecruitmentController {

    public boolean addCandidate(Candidate candidate) throws SQLException {
        String sql = "INSERT INTO candidates (name, path, writing_score, coding_score, interview_score, final_score, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Connector.openConnect();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, candidate.getName());
            pstmt.setString(2, candidate.getPath());
            pstmt.setDouble(3, candidate.getWritingScore());
            pstmt.setDouble(4, candidate.getCodingScore());
            pstmt.setDouble(5, candidate.getInterviewScore());
            pstmt.setDouble(6, candidate.getFinalScore());
            pstmt.setString(7, candidate.getStatus());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        candidate.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error adding candidate: " + e.getMessage());
            throw new SQLException("Failed to add candidate.", e);
        } finally {
            closeResources(pstmt);
            closeResources(conn);
        }
    }

    public List<Candidate> getAllCandidates() throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT id, name, path, writing_score, coding_score, interview_score FROM candidates";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Connector.openConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String path = rs.getString("path");
                double writingScore = rs.getDouble("writing_score");
                double codingScore = rs.getDouble("coding_score");
                double interviewScore = rs.getDouble("interview_score");

                Candidate candidate;
                if ("Android Developer".equals(path)) {
                    candidate = new AndroidDeveloper(id, name, writingScore, codingScore, interviewScore);
                } else if ("Web Developer".equals(path)) {
                    candidate = new WebDeveloper(id, name, writingScore, codingScore, interviewScore);
                } else {
                    continue;
                }
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all candidates: " + e.getMessage());
            throw new SQLException("Failed to retrieve candidates.", e);
        } finally {
            closeResources(rs);
            closeResources(stmt);
            closeResources(conn);
        }
        return candidates;
    }

    public boolean updateCandidate(Candidate candidate) throws SQLException {
        String sql = "UPDATE candidates SET name = ?, path = ?, writing_score = ?, coding_score = ?, interview_score = ?, final_score = ?, status = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Connector.openConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, candidate.getName());
            pstmt.setString(2, candidate.getPath());
            pstmt.setDouble(3, candidate.getWritingScore());
            pstmt.setDouble(4, candidate.getCodingScore());
            pstmt.setDouble(5, candidate.getInterviewScore());
            pstmt.setDouble(6, candidate.calculateFinalScore());
            pstmt.setString(7, candidate.getSelectionStatus());
            pstmt.setInt(8, candidate.getId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating candidate: " + e.getMessage());
            throw new SQLException("Failed to update candidate.", e);
        } finally {
            closeResources(pstmt);
            closeResources(conn);
        }
    }

    public boolean deleteCandidate(int id) throws SQLException {
        String sql = "DELETE FROM candidates WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Connector.openConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting candidate: " + e.getMessage());
            throw new SQLException("Failed to delete candidate.", e);
        } finally {
            closeResources(pstmt);
            closeResources(conn);
        }
    }

    private void closeResources(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                System.err.println("Error closing resource: " + e.getMessage());
            }
        }
    }
}
