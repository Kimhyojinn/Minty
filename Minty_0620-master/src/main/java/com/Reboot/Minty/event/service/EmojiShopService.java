package com.Reboot.Minty.event.service;

import com.Reboot.Minty.event.entity.EmojiShop;
import com.Reboot.Minty.event.repository.EmojiShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmojiShopService {

    private EmojiShopRepository emojiShopRepository;

    public EmojiShopService(EmojiShopRepository emojiShopRepository) {
        this.emojiShopRepository = emojiShopRepository;
    }
    public List<EmojiShop> getAllEmojiShops() {
        return emojiShopRepository.findAll();
    }
    public EmojiShop getEmojiShopById(Long id) {
        return emojiShopRepository.findById(id).orElse(null);
    }

    public void saveEmoji(EmojiShop emojiShop){ emojiShopRepository.save(emojiShop);}
}






