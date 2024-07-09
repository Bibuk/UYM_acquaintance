package com.acquaintance.acquaintance;

import com.acquaintance.acquaintance.Entity.Wall;
import com.acquaintance.acquaintance.Repository.WallRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private WallRepo WallRepo;
    @Override
    public void run(String... args) throws Exception {
        if (WallRepo.count() == 0) {
            WallRepo.save(new Wall("Расскажи о себе"));
        }
    }
}
