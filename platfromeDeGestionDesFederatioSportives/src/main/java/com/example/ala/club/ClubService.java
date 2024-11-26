package com.example.ala.club;

import com.example.ala.sport.PageResponse;
import com.example.ala.File.FileStorageService;
import com.example.ala.user.User;

import com.example.ala.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubService {
    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;
    private final FileStorageService fileStorageService;
    private final UserRepository userRepository;

    public PageResponse<ClubResponse> findClubBySport(Authentication connectedUser,int page,int size) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page<Club> clubResponses =  clubRepository.findBySportName(user.getSport().getName(),pageable);
        List<ClubResponse> clubs = clubResponses.stream().map(clubMapper::fromClub).toList();
        return new PageResponse<>(
                clubs,
                clubResponses.getNumber(),
                clubResponses.getSize(),
                clubResponses.getTotalElements(),
                clubResponses.getTotalPages(),
                clubResponses.isFirst(),
                clubResponses.isLast()
        );

    }


    public Long saveClub(ClubRequest request, MultipartFile multipartFile, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Club club = clubMapper.toClub(request,user.getSport());
        club.setClubPicture(fileStorageService.saveClubFile(multipartFile, club.getName()));
        clubRepository.save(club);
        return club.getId();
    }

    public ClubResponse getBookById(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Club not found"));
        return clubMapper.fromClub(club);
    }


    public Long updateClub(Long id,ClubRequest request,MultipartFile multipartFile) {
        Club club = clubRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Club not found"));
        club.setName(request.getName());
        club.setAddress(request.getAddress());
        club.setDescription(request.getDescription());
        club.setEmail(request.getEmail());
        club.setPhoneNumber(request.getPhoneNumber());
        club.setClubPicture(fileStorageService.saveClubFile(multipartFile, club.getName()));
        clubRepository.save(club);
        return club.getId();
    }

    public List<ClubResponse> getAllClub(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
       return clubRepository.findBySportId(user.getSport().getId()).stream().map(clubMapper::fromClub).toList();
    }
}
