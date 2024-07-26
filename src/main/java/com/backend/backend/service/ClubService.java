package com.backend.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.backend.model.Club;
import com.backend.backend.repository.ClubRepository;

import java.util.List;

@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club getClubById(String id) {
        return clubRepository.findById(id).orElse(null);
    }

    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    public void deleteClub(String id) {
        clubRepository.deleteById(id);
    }
}
