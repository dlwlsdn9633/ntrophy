package com.ntrophy.repository.admin;

import com.ntrophy.domain.admin.AdminYoutube;
import com.ntrophy.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisAdminRepository implements AdminRepository {
    private final AdminMapper adminMapper;
    @Override
    public List<AdminYoutube> youtubeList() {
        return adminMapper.youtubeList();
    }
    @Override
    public int checkYoutube(int idx) {
        return adminMapper.countYoutubeUrl(idx);
    }
    @Override
    public int insertYoutube(AdminYoutube adminYoutube) {
        return adminMapper.insertYoutubeUrl(adminYoutube);
    }
    @Override
    public int updateYoutube(AdminYoutube adminYoutube) {
        return adminMapper.updateYoutubeUrl(adminYoutube);
    }
}
