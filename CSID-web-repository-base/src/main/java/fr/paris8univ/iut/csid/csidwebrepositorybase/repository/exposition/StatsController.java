package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.exposition;



import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.core.Stats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.core.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/repositories")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService=statsService;
    }

    @GetMapping("/{name}/stat")
    public List<Stats> getStats(@PathVariable String name, @RequestParam String entry_type) {
        return statsService.getStats(name, entry_type);
    }

}
