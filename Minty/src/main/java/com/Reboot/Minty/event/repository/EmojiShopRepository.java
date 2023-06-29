package com.Reboot.Minty.event.repository;

import com.Reboot.Minty.event.entity.EmojiShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmojiShopRepository extends JpaRepository<EmojiShop, Long> {

}
