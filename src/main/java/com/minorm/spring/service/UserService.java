package com.minorm.spring.service;

import com.minorm.spring.database.entity.User;
import com.minorm.spring.database.repository.UserRepository;
import com.minorm.spring.dto.UserCreateEditDto;
import com.minorm.spring.dto.UserFilter;
import com.minorm.spring.dto.UserReadDto;
import com.minorm.spring.mapper.UserCreateEditMapper;
import com.minorm.spring.mapper.UserReadMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final ImageService imageService;

//    @PostFilter("filterObject.role.name().equals('ADMIN')") Будет работать только с коллекциями
//    @PostFilter("@companyService.findById(filterObject.company.id()).isPresent()")
    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        Specification<User> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filter.firstname() != null && !filter.firstname().isEmpty()) {
                predicate = criteriaBuilder.and(
                        predicate, criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("firstname")),
                                "%" + filter.firstname().toLowerCase() + "%"
                        )
                );
            }

            if (filter.lastname() != null && !filter.lastname().isEmpty()) {
                predicate = criteriaBuilder.and(
                        predicate, criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("lastname")),
                                "%" + filter.lastname().toLowerCase() + "%"
                        )
                );
            }

            if (filter.birthDate() != null) {
                predicate = criteriaBuilder.and(
                        predicate, criteriaBuilder.lessThanOrEqualTo(
                                root.get("birthDate"), filter.birthDate()
                        )
                );
            }

            return predicate;
        };

        return userRepository.findAll(spec, pageable)
                .map(userReadMapper::map);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    public Optional<byte[]> findAvatar(Long id) {
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return userCreateEditMapper.map(dto);
                })
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> {
                    uploadImage(userDto.getImage());
                    return userCreateEditMapper.map(userDto, entity);
                })
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + username));
    }



}