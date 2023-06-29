package com.Reboot.Minty.event.controller;

import com.Reboot.Minty.event.entity.EmojiShop;
import com.Reboot.Minty.event.service.EmojiShopService;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class EmojiController {

    private final EmojiShopService emojiShopService;

    @Autowired
    private Storage storage;

    @Autowired
    public EmojiController(EmojiShopService emojiShopService) {
        this.emojiShopService = emojiShopService;
    }

    @GetMapping("/emojiRegister")
    public String showRegistrationForm(Model model) {
        model.addAttribute("emojiShop", new EmojiShop());
        return "event/emojiRegister";
    }

    @GetMapping("/emojiDetail/{id}")
    public String showEmojiDetail(@PathVariable("id") Long id, Model model) {
        EmojiShop emojiShop = emojiShopService.getEmojiShopById(id);
        model.addAttribute("emojiShop", emojiShop);
        return "event/emojiDetail";
    }

    @GetMapping("/emojiList")
    public String showEmojiList(Model model) {
        List<EmojiShop> emojiShops = emojiShopService.getAllEmojiShops();
        model.addAttribute("emojiShops", emojiShops);
        return "event/emojiList";
    }

    @Value("${spring.cloud.gcp.storage.credentials.bucket}")
    private String bucketName;

    @PostMapping("/emojiRegister")
    public String registerEmoticon(@ModelAttribute EmojiShop emojiShop, @RequestParam("imageFiles") List<MultipartFile> imageFiles, HttpSession session) {
        List<String> imagePaths = new ArrayList<>();
        for (MultipartFile imageFile : imageFiles) {
            if (!imageFile.isEmpty()) {
                try {
                    String uuid = UUID.randomUUID().toString();
                    String filePath = "images/" + uuid + ".jpg";
                    BlobInfo blobInfo = storage.create(
                            BlobInfo.newBuilder(bucketName, filePath)
                                    .setContentType("image/jpg")
                                    .build(),
                            imageFile.getInputStream()
                    );
                    imagePaths.add(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        emojiShop.setImages(imagePaths);
        emojiShopService.saveEmoji(emojiShop);
        return "redirect:/emojiList";
    }
}