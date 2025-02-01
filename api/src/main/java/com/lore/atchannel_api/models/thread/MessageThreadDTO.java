package com.lore.atchannel_api.models.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageThreadDTO {
    
    private String nickname = "";
    private String title = "";
    private String content = "";
    private String image_url = "";

}