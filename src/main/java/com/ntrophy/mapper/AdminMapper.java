package com.ntrophy.mapper;

import com.ntrophy.domain.admin.AdminYoutube;

import java.util.List;

public interface AdminMapper {
    List<AdminYoutube> youtubeList();
    int countYoutubeUrl(int idx);
    int insertYoutubeUrl(AdminYoutube adminYoutube);
    int updateYoutubeUrl(AdminYoutube adminYoutube);
}
