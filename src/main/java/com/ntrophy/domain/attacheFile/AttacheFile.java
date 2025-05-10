package com.ntrophy.domain.attacheFile;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttacheFile {
    private int no;
    private String tablename;
    private int commonNo;
    private String originalFilename;
    private String storeFilename;
    private LocalDateTime registerDate;
}
