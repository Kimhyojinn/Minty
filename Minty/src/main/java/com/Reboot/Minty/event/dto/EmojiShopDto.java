package com.Reboot.Minty.event.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmojiShopDto {
    private String name;
    private int price;
    private List<String> images = new ArrayList<>();

}