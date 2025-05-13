package com.ntrophy.domain.attacheFile;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttacheFile {
    private int id;
    private int commonId;
    private String tablename;
    private String originalFilename;
    private String storeFilename;
    private LocalDateTime registerDate;
}
