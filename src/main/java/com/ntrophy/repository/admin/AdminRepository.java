package com.ntrophy.repository.admin;

import com.ntrophy.domain.admin.AdminYoutube;

import java.util.List;

public interface AdminRepository {
    List<AdminYoutube> youtubeList();
    int checkYoutube(int idx);
    int insertYoutube(AdminYoutube adminYoutube);
    int updateYoutube(AdminYoutube adminYoutube);
}
