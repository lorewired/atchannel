package com.lore.atchannel_api.models.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {

    private String nickname = "";
    private String content = "";
    private String image_url = "";
    private String thread_id = "";
    
}