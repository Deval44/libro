package system.library.libro.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.library.libro.entity.Rack;
import system.library.libro.repository.RackRepository;
import system.library.libro.service.RackService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RackServiceImpl implements RackService {

    @Autowired
    private RackRepository rackRepository;

    @Override
    public List<Rack> getAllRacks() {
        return rackRepository.findAll();
    }

    @Override
    public List<Rack> addRacks(int num) {
        List<Rack> racks = new ArrayList<>();
        for(int i=0 ;i<num; i++){
            racks.add(new Rack());
        }

        rackRepository.saveAll(racks);
        return getAllRacks();
    }
}
