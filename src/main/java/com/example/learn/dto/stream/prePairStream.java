package com.example.learn.dto.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class prePairStream {
    private String streamKey;
    private String rtmpUrl;
    private String title;
    private String description;
    private String thumbnailUrl;
    private Long streamTypeId; // Assuming this is the ID of the stream type

}
