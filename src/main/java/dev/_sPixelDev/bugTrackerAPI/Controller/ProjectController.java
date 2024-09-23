package dev._sPixelDev.bugTrackerAPI.Controller;

import dev._sPixelDev.bugTrackerAPI.Entity.Developers;
import dev._sPixelDev.bugTrackerAPI.Entity.HttpResponseHandler;
import dev._sPixelDev.bugTrackerAPI.Repository.DeveloperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {
    private DeveloperRepo developerRepo;

    @Autowired
    public void setDeveloperRepo(DeveloperRepo developerRepo) { this.developerRepo = developerRepo;}

    @GetMapping("/get/devs")
    public ResponseEntity<Object> getDevelopers (@RequestParam(defaultValue = "0") int pageNumber) {
        try {
            Page<Developers> devs = developerRepo.findAll(PageRequest.of(pageNumber, 20, Sort.Direction.ASC, "id"));
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully added new Developer info to database", devs.getContent());
        }
        catch (Exception e) {
            return HttpResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Something went wrong", pageNumber);
        }
    };

    @PostMapping("/add/dev")
    public ResponseEntity<Object> createNewDev (@RequestBody Developers dev) {
        try {
            developerRepo.save(dev);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully added new Developer info to database", dev);
        }
        catch (Exception e) {
            return HttpResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Something went wrong", dev);
        }
    };
}
