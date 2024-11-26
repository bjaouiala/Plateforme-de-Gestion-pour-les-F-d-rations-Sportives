package com.example.ala.sport;

import com.example.ala.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportService {
    private final SportRepository repository;
    private final SportMapper mapper;


    public Long addSport(SportRequest request) {
        Sport sport = mapper.toSport(request);
        return repository.save(sport).getId();

    }


    public PageResponse<SportResponse> findAllSports(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("name").descending());
        Page<Sport> sports = repository.findAll(pageable);
        List<SportResponse> sportResponses =sports.stream().map(mapper::fromSport).toList();
        return new PageResponse<>(
                sportResponses,
                sports.getNumber(),
                sports.getSize(),
                sports.getTotalElements(),
                sports.getTotalPages(),
                sports.isFirst(),
                sports.isLast()
        );
    }
}
