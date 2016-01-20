package com.pw.wbd.bootstrap;

import com.pw.wbd.domain.entities.Building;
import com.pw.wbd.domain.entities.Developer;
import com.pw.wbd.domain.entities.House;
import com.pw.wbd.domain.entities.Project;
import com.pw.wbd.domain.repositories.BuildingRepository;
import com.pw.wbd.domain.repositories.DeveloperRepository;
import com.pw.wbd.domain.repositories.HouseRepository;
import com.pw.wbd.domain.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by arade on 20-Jan-16.
 */
@Component
public class DevLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Developer dev = new Developer();
        dev.setName("Dev10");
        Project p = new Project();
        p.setName("Projekt10");
        p.setDeveloper(dev);
        Building b = new Building();
        b.setBuildingNumber("5");
        b.setCity("Warszawa");
        b.setFlorQuantity(5);
        b.setPostCode("00-000");
        b.setType("Nowy");
        b.setProject(p);
        House h = new House();
        h.setArea(45.34);
        h.setBuilding(b);
        h.setLandArea(333.2);
        h.setPricePerMeter(5454);

//        developerRepository.save(dev);
//        projectRepository.save(p);
//        buildingRepository.save(b);
//        houseRepository.save(h);

        System.out.println("DONE");
    }
}
