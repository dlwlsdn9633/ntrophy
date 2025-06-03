package com.ntrophy.service;

import com.ntrophy.domain.admin.AdminYoutube;
import com.ntrophy.dto.admin.AdminRequestDto;
import com.ntrophy.repository.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    public List<AdminYoutube> youtubeList() {
        return adminRepository.youtubeList();
    }
    public boolean updateYoutubeUrls(AdminRequestDto adminRequestDto) {
        List<AdminYoutube> adminYoutubeList = new ArrayList<>();
        String[] youtubeUrls = adminRequestDto.getYoutubeUrls();
        for (int i = 0; i < youtubeUrls.length; i++) {
            adminYoutubeList.add(AdminYoutube.builder()
                    .idx(i + 1)  // 1부터 시작
                    .youtubeUrl(youtubeUrls[i])
                    .build());
        }
        adminYoutubeList.forEach((adminYoutube) -> {
            int check = adminRepository.checkYoutube(adminYoutube.getIdx());
            if (check > 0) {
                adminRepository.updateYoutube(adminYoutube);
            } else {
                adminRepository.insertYoutube(adminYoutube);
            }
        });
        return true;
    }
}
