package system.library.libro.service;

import system.library.libro.entity.Copy;
import system.library.libro.entity.Rack;

import java.util.List;

public interface RackService {
    List<Rack> getAllRacks();

    List<Rack> addRacks(int num);

    Copy addCopyToRack(Long copyId);
}
