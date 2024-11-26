package com.example.ala.adminClub;

import com.example.ala.File.FileStorageService;
import com.example.ala.authentication.RegistrationRequest;
import com.example.ala.club.Club;
import com.example.ala.club.ClubRepository;
import com.example.ala.email.EmailService;
import com.example.ala.email.EmailTemplateName;
import com.example.ala.sport.PageResponse;
import com.example.ala.sport.Sport;
import com.example.ala.sport.SportRepository;
import com.example.ala.user.User;
import com.example.ala.user.UserMapper;
import com.example.ala.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminClubService {
    @Value("${application.mail.login-url}")
    private String loginUrl;
    private final SportRepository sportRepository;
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final FileStorageService fileStorageService;
    private final AdminMapper adminMapper;
    private final PasswordEncoder encoder;
    private final ClubRepository clubRepository;
    public void registerAdmin(RegistrationRequest request, MultipartFile picture, Authentication connectedUser) throws MessagingException {
        User user = (User) connectedUser.getPrincipal();
        Sport sport = sportRepository.findById(user.getSport().getId())
                .orElseThrow(()->new EntityNotFoundException("there is no sport with thee given name"));
        User admin = mapper.toUser(request, picture);
        admin.setSport(sport);
        userRepository.save(admin);
        emailService.senConfirmationAccount(admin.getEmail(), "bjaouiala@gmail.com", admin.fullName(), EmailTemplateName.ADMIN_ACTIVATION_ACCOUNT,"Admin confirmation Account",loginUrl,request.getPassword());

    }

    public PageResponse<AdminResponse> getAllClubAdmin(Integer page, Integer size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page<User> adminClubResponse = userRepository.findByRoleAndSportName(pageable,user.getSport().getId());
        List<AdminResponse> adminResponses = adminClubResponse.stream().map(mapper::fromUser).toList();
        return new PageResponse<>(
                adminResponses,
                adminClubResponse.getNumber(),
                adminClubResponse.getSize(),
                adminClubResponse.getTotalElements(),
                adminClubResponse.getTotalPages(),
                adminClubResponse.isFirst(),
                adminClubResponse.isLast()
        );
    }


    public Long changeAccountStatus(Long id, boolean accountLocked) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("user not found"));
        user.setAccountLocked(accountLocked);
        userRepository.save(user);
        return id;
    }

    public Long updateAdminClub(RegistrationRequest request, MultipartFile picture, Authentication connectedUser,Long id) {
        User connectedUsers = (User) connectedUser.getPrincipal();
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("user not found"));
        user.setEmail(request.getEmail());
        user.setClub(connectedUsers.getClub());
        user.setClub(connectedUsers.getClub());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setUserPicture(fileStorageService.saveFile(picture,request.getFirstname()));
        userRepository.save(user);
        return user.getId();

    }

    public AdminResponse getAdminById(Long id) {
      User user = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("user not found"));
        System.out.println(user);
        return adminMapper.fromUser(user);
    }
}
