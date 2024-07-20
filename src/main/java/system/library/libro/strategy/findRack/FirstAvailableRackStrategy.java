package system.library.libro.strategy.findRack;

import lombok.extern.slf4j.Slf4j;
import system.library.libro.entity.Book;
import system.library.libro.entity.Copy;
import system.library.libro.entity.Rack;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class FirstAvailableRackStrategy implements RackFindingStrategy {

    private final Book book;
    private final List<Rack> racks;

    public FirstAvailableRackStrategy(Book book, List<Rack> racks){
        this.book = book;
        this.racks = racks;
    }

    @Override
    public Rack find() {
        Set<Long> occupiedRacks = book.getCopies()
                .stream()
                .map(Copy::getRack)
                .filter(Objects::nonNull)
                .map(Rack::getId)
                .collect(Collectors.toSet());

        racks.sort(Comparator.comparingLong(Rack::getId));
        for(Rack rack : racks){
            if(!occupiedRacks.contains(rack.getId())){
                return rack;
            }
        }
        return null;
    }
}
